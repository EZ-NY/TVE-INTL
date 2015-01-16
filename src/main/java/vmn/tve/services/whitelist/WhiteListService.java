package vmn.tve.services.whitelist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import vmn.tve.services.whitelist.client.DeviceType;
import vmn.tve.services.whitelist.client.Environment;
import vmn.tve.services.whitelist.client.Exclusion;
import vmn.tve.services.whitelist.client.Exclusions;
import vmn.tve.services.whitelist.client.PlatformType;
import vmn.tve.services.whitelist.client.Provider;
import vmn.tve.services.whitelist.client.ProvidersList;
import vmn.tve.services.whitelist.client.RequestorIDType;
import vmn.tve.services.whitelist.parser.WhiteListParser;
import vmn.tve.utils.Constants;

/**
 * Service to work with whitelist.
 */
@Service
public class WhiteListService {

    @Autowired
    private ApplicationContext context;

    /**
     * @param locale - language_countryCode value
     * @return environment element from whitelist
     */
    public Environment getWhiteList(final String locale) {
        return getWhiteListParser().parse(locale.split("_")[1].toLowerCase());
    }

    /**
     * @param locale - language_countryCode value
     * @param componentId - platform
     * @param requestorId - brand
     * @param deviceType - device
     * @return provider list filtered according to platform, brand and device
     */
    public List<Provider> getProviders(final String locale, final String componentId, final String requestorId,
            final String deviceType) {
        List<Provider> result = new ArrayList<Provider>();
        Environment environment = getWhiteList(locale);
        ProvidersList providersList = environment.getProvidersList();
        List<Provider> providers = providersList.getProvider();
        Iterator<Provider> providersIterator = providers.iterator();
        while (providersIterator.hasNext()) {
            Provider provider = providersIterator.next();
            List<Exclusions> providerExclusions = provider.getExclusions();
            if (!(providerExclusions == null || providerExclusions.isEmpty())) {
                if (!isExcluded(providerExclusions, deviceType, requestorId, componentId)) {
                    result.add(provider);
                }
            } else {
                result.add(provider);
            }
        }
        return result;
    }

    private boolean isExcluded(final List<Exclusions> providerExclusions, final String deviceType,
            final String requestorId, final String componentId) {
        for (Exclusions providerExclusion : providerExclusions) {
            List<Exclusion> exclusions = providerExclusion.getExclusion();
            for (Exclusion exclusion : exclusions) {
                DeviceType excDeviceType = exclusion.getDeviceType();
                PlatformType platformType = exclusion.getPlatform();
                RequestorIDType requestorIDType = exclusion.getRequestorID();
                if ((excDeviceType.value().equalsIgnoreCase(DeviceType.ALL.value()) || excDeviceType.value()
                        .equalsIgnoreCase(deviceType))
                        && (requestorIDType.value().equalsIgnoreCase(RequestorIDType.ALL.value()) || requestorIDType
                                .value().equalsIgnoreCase(requestorId))
                        && (platformType.value().equalsIgnoreCase(PlatformType.ALL.value()) || platformType.value()
                                .equalsIgnoreCase(componentId))) {
                    return true;
                }
            }
        }
        return false;
    }

    private WhiteListParser getWhiteListParser() {
        return (WhiteListParser) context.getBean(Constants.WHITE_LIST_PARSER);
    }

}
