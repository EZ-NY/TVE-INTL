package vmn.tve.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * CheckPermissionsRespDTO object converted to json and returned to centralized service.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckPermissionsRespDTO extends ResponseDTO {
    private Integer authZStatus;
    private String state = "";
    private String mvpdId = "";
    private String resourceId = "";

    public Integer getAuthZStatus() {
        return authZStatus;
    }

    public void setAuthZStatus(final Integer authZStatus) {
        this.authZStatus = authZStatus;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getMvpdId() {
        return mvpdId;
    }

    public void setMvpdId(final String mvpdId) {
        this.mvpdId = mvpdId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

}
