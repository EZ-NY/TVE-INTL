package vmn.tve.services.errors;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import vmn.tve.config.cache.CacheStorage;
import vmn.tve.utils.Constants;

/**
 * ErrorMappingService intended to get errormapping.xml file.
 */
@Service
public class ErrorMappingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorMappingService.class);

    @Autowired
    private ApplicationContext context;

    @Value("${default.errormapping.provider.id}")
    private String defaultId;

    @Value("${errormapping.location}")
    private String location;

    /**
     * @param providerId - mvpdid
     * @return list of error codes configured in errormapping file for
     *         appropriate provider
     */
    @Cacheable(value = CacheStorage.ERROR_MAPPING, key = "#providerId")
    public List<ErrorCode> getErrorCodes(final String providerId) {
        List<ErrorCode> result = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse((context.getResource("classpath:" + location).getFile()));
            JAXBContext jaxbContext = JAXBContext.newInstance(ErrorMapping.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ErrorMapping mapping = (ErrorMapping) jaxbUnmarshaller.unmarshal(doc);
            List<Provider> providers = mapping.getProvider();
            Iterator<Provider> iterator = providers.iterator();
            while (iterator.hasNext()) {
                Provider provider = iterator.next();
                if (provider.getId().equalsIgnoreCase(defaultId)) {
                    result = provider.getErrorCode();
                } else if (provider.getId().equalsIgnoreCase(providerId)) {
                    result = provider.getErrorCode();
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.error(Constants.MSG_PROVIDER_ID + providerId + " " + Constants.MSG_ERRORMAPPING + location);
        }
        return result;
    }
}
