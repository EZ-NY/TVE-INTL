package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ISIS GeneralResponse response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralResponse {
    private Response response;

    public Response getResponse() {
        return this.response;
    }

    public void setResponse(final Response response) {
        this.response = response;
    }
}
