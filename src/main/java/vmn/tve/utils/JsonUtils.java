package vmn.tve.utils;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtils class to convert json objects to java classes and vice versa.
 */
public final class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    /**
     * @param <T> - java bean name
     * @param response - json string that has to be converted to java bean
     * @param clazz - java bean name
     * @return - java bean object
     */
    public static <T> T convertToBean(final String response, final Class<T> clazz) {
        T result = null;
        try {
            result = getMapper().readValue(response, clazz);
        } catch (IOException e) {
            LOGGER.error(Constants.MSG_JSON_PARSE + response);
        }
        return result;
    }

    /**
     * @param <T> - java bean name
     * @param response - stream that has to be converted to java bean
     * @param clazz - java bean name
     * @return - java bean object
     */
    public static <T> T convertToBean(final InputStream response, final Class<T> clazz) {
        T result = null;
        try {
            result = getMapper().readValue(response, clazz);
        } catch (IOException e) {
            LOGGER.error(Constants.MSG_JSON_PARSE + response);
        }
        return result;
    }

    /**
     * @param object - nay java object
     * @return json string
     */
    public static String convertToString(final Object object) {
        String result = null;
        try {
            result = JsonUtils.getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error(Constants.MSG_JSON_CREATE);
        }
        return result;
    }
}
