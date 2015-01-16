package vmn.tve.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vmn.tve.dto.ProviderDTO;
import vmn.tve.dto.ProvidersListDTO;
import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.errors.ResponseCode;
import vmn.tve.services.errors.ResponseGroup;
import vmn.tve.services.isis.ISISService;
import vmn.tve.services.isis.ImagePlacing;
import vmn.tve.services.isis.domain.CMSData;
import vmn.tve.services.request.IncomingRequest;
import vmn.tve.services.request.IncomingRequestService;
import vmn.tve.services.whitelist.WhiteListService;
import vmn.tve.services.whitelist.client.Provider;
import vmn.tve.utils.Constants;
import vmn.tve.utils.JsonUtils;

/**
 * ProvidersMerger service intended to collect information from whitelist and
 * isis and combine it.
 */
@Service
public class ProvidersMerger {
    private static final Logger LOG = LoggerFactory.getLogger(ProvidersMerger.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IncomingRequestService irs;

    @Autowired
    private Environment env;

    @Autowired
    private HashMap<String, String> protocols;

    /**
     * @return provider list with data received from whitelist and isis
     */
    public String getProviders() {
        IncomingRequest rd = irs.getIncomingRequest();
        List<Provider> providers = getWhiteListService().getProviders(rd.getLocale(), rd.getComponentId(),
                rd.getRequestorId(), rd.getDeviceType());
        Set<String> wlProviderIDs = new HashSet<String>();
        for (Provider provider : providers) {
            // To lower case as ISIS urlKey mtvi:urlKey is in lower case
            wlProviderIDs.add(provider.getId().toLowerCase());
        }
        LOG.debug("Got providers: " + wlProviderIDs + " by exclusions: " + rd.getComponentId() + Constants.COMMA
                + rd.getRequestorId() + Constants.COMMA + rd.getDeviceType());
        Map<String, CMSData> cmsData = getISISService().getProviderData(rd.getRequestorId().toLowerCase(),
                rd.getComponentId().toLowerCase(), rd.getLocale().toLowerCase());
        LOG.debug(Constants.MSG_PARSED_ISIS_RESPONSE + cmsData);
        // Log Providers which are not configured in ISIS
        wlProviderIDs.removeAll(cmsData.keySet());
        if (!wlProviderIDs.isEmpty()) {
            LOG.error(Constants.MSG_ISIS_DATA_MISSED + wlProviderIDs);
        }
        ProvidersListDTO response = mergeResponse(providers, cmsData);
        return JsonUtils.convertToString(response);
    }

    private ProvidersListDTO mergeResponse(final List<Provider> providers, final Map<String, CMSData> cmsData) {
        List<ProviderDTO> providersDTO = new ArrayList<ProviderDTO>();
        int countPrimaryProviders = env.getProperty("count.primary.providers", Integer.class);
        for (Provider provider : providers) {
            ProviderDTO providerDTO = new ProviderDTO();
            // To lower case is an ISIS MVPD id variant (as urlKey mtvi:urlKey
            // in lower case)
            String providerId = provider.getId().toLowerCase();
            // Provider.getId() is Whitelist MVPD id variant
            providerDTO.setId(provider.getId());
            providerDTO.setAltName(provider.getAltName());
            providerDTO.setAuthStandard(protocols.get(provider.getServiceManager()));

            CMSData providerIsisData = cmsData.get(providerId);
            if (providerIsisData != null) {
                providerDTO.setDisplayName(providerIsisData.getDisplayName());
                populateProviderImages(providerDTO, providerIsisData.getImages());
                providerDTO.setPrimary(countPrimaryProviders-- > 0 ? true : false);
                providersDTO.add(providerDTO);
            }
        }

        ProvidersListDTO response = new ProvidersListDTO();
        response.setProvidersList(providersDTO);
        response.setStatus(Constants.STATUS_SUCCESS);
        response.setFunction(Constants.API_GET_PROVIDERS_LIST);
        return response;
    }

    /**
     * Validates provider, whether it exists in whitelist and has logo
     * configuration in ISIS.
     *
     * @return valid provider instance
     */
    public Provider validateProvider() {
        IncomingRequest rd = irs.getIncomingRequest();
        List<Provider> providers = getWhiteListService().getProviders(rd.getLocale(), rd.getComponentId(),
                rd.getRequestorId(), rd.getDeviceType());
        LOG.debug("Got " + providers.size() + " providers from Whitelist [" + rd.getLocale() + "]");
        Provider provider = null;
        for (Provider p : providers) {
            if (p.getId().equalsIgnoreCase(rd.getMvpdId())) {
                provider = p;
                break;
            }
        }
        if (provider == null) {
            throw new LocalServiceException(ResponseGroup.WHITELIST_ERROR, ResponseCode.WHITELIST_INVALID_PROVIDER,
                    HttpStatus.BAD_REQUEST);
        }

        Map<String, CMSData> cmsData = getISISService().getProviderData(rd.getRequestorId().toLowerCase(),
                rd.getComponentId().toLowerCase(), rd.getLocale().toLowerCase());
        boolean isValidByISIS = false;
        for (String id : cmsData.keySet()) {
            isValidByISIS |= id.equalsIgnoreCase(rd.getMvpdId());
        }
        if (!isValidByISIS) {
            throw new LocalServiceException(ResponseGroup.ISIS_ERROR, ResponseCode.ISIS_PROVIDER_CONFIGURATION_MISSING,
                    HttpStatus.NOT_FOUND);
        }
        return provider;
    }

    /**
     * Fills the ProviderDTO with LogoImages according to Images placements
     * priority rules.
     *
     * @param providerDTO - ProviderDTO instance to fill images
     * @param images - images received from provider
     */
    public void populateProviderImages(final ProviderDTO providerDTO, final Map<ImagePlacing, String> images) {
        String isisProviderId = providerDTO.getId().toLowerCase();
        String providerImageUrlPrefix = env.getProperty(isisProviderId + ".isis.url.image.prefix");
        if (providerImageUrlPrefix == null) {
            LOG.error("Missing ISIS MVPD_ID.isis.url.image.prefix mapping for MVPD_ID=" + isisProviderId);
            throw new LocalServiceException(ResponseGroup.LOCAL_SERVICE_CONFIGURATION_ERROR,
                    ResponseCode.ISIS_PROVIDER_CONFIGURATION_MISSING, HttpStatus.NOT_FOUND);
        }

        // Picker logo is mandatory
        if (images == null || !images.containsKey(ImagePlacing.PICKER)) {
            LOG.error(Constants.MSG_PICKER_LOGO_UNDEFINED + providerDTO.getId());
            throw new LocalServiceException(ResponseGroup.ISIS_ERROR, ResponseCode.ISIS_PICKER_UNSPECIFIED,
                    HttpStatus.NOT_FOUND);
        }

        // Substitute missing image URLs with another, considering image placing
        // priority
        ImagePlacing[] values = ImagePlacing.values();
        int length = values.length;
        for (int i = 1; i < length; i++) {
            if (images.get(values[i]) != null) {
                for (int l = i + 1; l < length; l++) {
                    if (images.get(values[l]) == null) {
                        images.put(values[l], images.get(values[i]));
                    }
                }
                break;
            } else {
                for (int l = i + 1; l < length; l++) {
                    if (images.get(values[l]) != null) {
                        images.put(values[i], images.get(values[l]));
                        i--;
                        break;
                    } else if (l == length - 1) {
                        if (images.get(values[0]) != null) {
                            for (i = 1; i < length; i++) {
                                images.put(values[i], images.get(values[0]));
                            }
                        }
                    }
                }
            }
        }

        for (ImagePlacing p : ImagePlacing.values()) {
            // TODO URL format processing
            if (!images.get(p).startsWith("mgid:file:gsp:scenic:")) {
                LOG.error("unknown ISIS URL prefix format");
                throw new LocalServiceException(ResponseGroup.ISIS_ERROR, ResponseCode.ISIS_UNKNOWN_IMAGE_FORMAT,
                        HttpStatus.NOT_FOUND);
            }
        }

        providerDTO.setPickerLogoUrl(providerImageUrlPrefix + images.get(ImagePlacing.PICKER));
        providerDTO.setDefaultLogoUrl(providerImageUrlPrefix + images.get(ImagePlacing.DEFAULT));
        providerDTO.setCobrandingLogoUrl(providerImageUrlPrefix + images.get(ImagePlacing.COBRANDING));
        providerDTO.setLogoutLogoUrl(providerImageUrlPrefix + images.get(ImagePlacing.LOGOUT));
    }

    private WhiteListService getWhiteListService() {
        return (WhiteListService) context.getBean(Constants.WHITE_LIST_SERVICE);
    }

    private ISISService getISISService() {
        return (ISISService) context.getBean(Constants.ISIS_SERVICE);
    }

}
