package vmn.tve.dto.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ErrorResponseDTO object converted to json and returned to centralized service.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponseDTO {
    private String status = "";
    private String group = "";
    @JsonProperty(value = "error_code")
    private String errorCode = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }
}
