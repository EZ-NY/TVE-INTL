package vmn.tve.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ResponseDTO object converted to json and returned to centralized service.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {
    private String status = "";
    private String function = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(final String function) {
        this.function = function;
    }
}
