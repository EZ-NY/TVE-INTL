package vmn.tve.services.whitelist.parser;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import vmn.tve.config.cache.CacheStorage;
import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.errors.ResponseCode;
import vmn.tve.services.errors.ResponseGroup;
import vmn.tve.services.whitelist.client.Environment;
import vmn.tve.utils.Constants;

/**
 * Service to parse whitelist.
 */
@Service
public class WhiteListParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(WhiteListParser.class);

    @Value("${whitelist.url.template}")
    private String whitelistUrlTemplate;

    /**
     * @param countryCode - country code to get whitelist
     * @return whitelist environment element
     */
    @Cacheable(value = CacheStorage.WHITELIST, key = "#countryCode")
    public Environment parse(final String countryCode) {
        Environment environment = null;
        String uri = buildUrl(countryCode);
        try {
            URL url = new URL(uri);
            URLConnection connection = url.openConnection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(connection.getInputStream());

            JAXBContext jaxbContext = JAXBContext.newInstance(Environment.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            environment = (Environment) jaxbUnmarshaller.unmarshal(doc);

        } catch (Exception e) {
            LOGGER.error(Constants.MSG_WHITE_LIST_PARSE_ERROR + uri);
            throw new LocalServiceException(ResponseGroup.WHITELIST_ERROR, ResponseCode.WHITELIST_PARSE_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return environment;
    }

    private String buildUrl(final String countryCode) {
        return whitelistUrlTemplate.replace("{countryCode}", countryCode);
    }
}
