package vmn.tve.services.isis;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vmn.tve.config.cache.CacheStorage;
import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.errors.ResponseCode;
import vmn.tve.services.errors.ResponseGroup;
import vmn.tve.services.isis.domain.CMSData;
import vmn.tve.services.isis.domain.CollectionItems;
import vmn.tve.services.isis.domain.Docs;
import vmn.tve.services.isis.domain.GeneralResponse;
import vmn.tve.services.isis.domain.ImageAssetRefs;
import vmn.tve.services.isis.domain.ImageItems;
import vmn.tve.services.isis.domain.ModuleItems;
import vmn.tve.services.isis.domain.Response;
import vmn.tve.utils.Constants;
import vmn.tve.utils.JsonUtils;

import com.google.common.base.Charsets;

/** ISIS integration service. */
@Service
public class ISISService {
    private static final Logger LOG = LoggerFactory.getLogger(ISISService.class);
    private static final String PLATFORM = "0dc9bf65-5ac0-42e7-9be4-90ef5967e40d,1db04516-46df-404e-bcd1-ef04c6550eca";
    private static final String TVE_URL_KEY_PREFIX = "tve_";

    @Autowired
    private Environment env;

    /**
     * Returns LogoImages and Title for provider MVPD IDs passed in parameters.
     * @param brand - requestor ID
     * @param platform - component ID
     * @param locale - locale
     * @return Map where key is Provider MVPD ID and values is ISIS data
     */
    @Cacheable(value = CacheStorage.ISIS)
    public Map<String, CMSData> getProviderData(final String brand, final String platform, final String locale) {
        Map<String, CMSData> result;
        String url = buildURL(locale.split("_")[1].toLowerCase(), brand, platform);
        LOG.debug(Constants.MSG_OPEN_CONNECTION + url);
        try (InputStream is = new URL(url).openStream()) {
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, Charsets.UTF_8.name());
            result = parseResponse(writer.toString());
        } catch (IOException e) {
            LOG.error(Constants.MSG_ISIS_CONNECTION_ERROR + url);
            e.printStackTrace();
            throw new LocalServiceException(ResponseGroup.ISIS_ERROR, ResponseCode.ISIS_CONNECTION_ERROR,
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
        return result;
    }

    /**
     * @param isisResponse - response in json format received from isis service
     * @return - map: key = mvpdid; value = data with displayName and urls
     */
    public Map<String, CMSData> parseResponse(final String isisResponse) {
        LOG.debug(Constants.MSG_ISIS_RESPONSE + isisResponse);
        Map<String, CMSData> result = new HashMap<String, CMSData>();
        GeneralResponse r = JsonUtils.convertToBean(isisResponse, GeneralResponse.class);

        if (r.getResponse() == null || r.getResponse().getDocs() == null) {
            return result;
        }
        Response response = r.getResponse();
        Docs[] docs = response.getDocs();
        if (docs.length < 1) {
            return result;
        }
        for (Docs doc : docs) {
            ModuleItems[] mi = doc.getModuleItems();
            if (mi != null) {
                for (ModuleItems moduleItem : mi) {
                    CollectionItems[] ci = moduleItem.getCollectionItems();
                    if (ci != null) {
                        for (CollectionItems collectionItem : ci) {
                            ImageItems[] ii = collectionItem.getImageItems();
                            if (ii != null) {
                                for (ImageItems imageItem : ii) {
                                    String providerId = parseProviderId(imageItem.getUrlKey());
                                    String providerTitle = imageItem.getTitle();
                                    if (result.get(providerId) == null) {
                                        result.put(providerId, new CMSData(providerTitle));
                                    }
                                    ImageAssetRefs[] iasRefs = imageItem.getImageAssetRefs();
                                    if (iasRefs != null) {
                                        for (ImageAssetRefs imageAssetRef : iasRefs) {
                                            String moduleType = moduleItem.getType().getTypeName();

                                            Map<ImagePlacing, String> images = result.get(providerId).getImages();
                                            ImagePlacing p = ImagePlacing.getByISISTypeRepresentation(moduleType);
                                            if (p != null) {
                                                images.put(p, imageAssetRef.getURI());
                                                LOG.debug("Found " + p.name() + " URL");
                                            } else {
                                                LOG.warn("Unknown module type (image type): " + moduleType);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /** @return MVPD ID based on urlKey of ISIS Image Items response tag */
    private String parseProviderId(final String s) {
        return s.replace(TVE_URL_KEY_PREFIX, "");
    }

    /**
     * Build ISIS connection URL based on request parameters.
     * @param countryCode - county code
     * @param brand - requestor ID
     * @param platform - component ID
     * @return ISIS connection URL
     */
    public String buildURL(final String countryCode, final String brand, final String platform) {
        String url = env.getProperty("isis.url.template");
        String stage = env.getProperty("isis.stage");
        String isisNamespace = env.getProperty("isis." + brand + "." + countryCode);
        if (isisNamespace == null) {
            throw new LocalServiceException(ResponseGroup.LOCAL_SERVICE_CONFIGURATION_ERROR,
                    ResponseCode.ISIS_BRAND_COUNTRYCODE_MAPPING_MISSING, HttpStatus.NOT_FOUND);
        }
        url = url.replace("{isis_namespace}", isisNamespace);
        url = url.replace("{brand}", brand);
        url = url.replace("{platform}", platform);
        url = url.replace("{stage}", stage);
        url = url.replace("{dpPlatform}", PLATFORM);
        return url;
    }
}
