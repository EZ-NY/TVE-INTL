package vmn.tve.providers;

import org.springframework.http.ResponseEntity;

/**
 * ResponseParser inteface has to be implemented to parse specific responses
 * returned from provider.
 */
public interface ResponseParser {

    /**
     * @return parsed information about access and refresh token in json format.
     */
    String parseAccessToken();

    /**
     * @return parsed information about token validation in json format.
     */
    String parseValidateToken();

    /**
     * @return parsed information about fresh access and refresh token in json
     *         format.
     */
    String parseRefreshToken();

    /**
     * @return parsed information with user permission to access specific
     *         resource in json format.
     */
    String parseResource();

    /**
     * @param response - response received from appropriate provider that has to
     *            be parsed
     */
    void setResponse(ResponseEntity<String> response);
}
