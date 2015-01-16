package vmn.tve.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ValidateTokenRespDTO object converted to json and returned to centralized service.
 *
 */
public class ValidateTokenRespDTO extends ResponseDTO {
    @JsonProperty(value = "user_id")
    private String userId = "";
    @JsonProperty(value = "user_sa")
    private String userSA = "";
    @JsonProperty(value = "user_display_name")
    private String userDisplayName = "";
    @JsonProperty(value = "user_session_id")
    private String userSessionId = "";
    @JsonProperty(value = "user_type")
    private String userType = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getUserSA() {
        return userSA;
    }

    public void setUserSA(final String userSA) {
        this.userSA = userSA;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(final String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(final String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(final String userType) {
        this.userType = userType;
    }

}
