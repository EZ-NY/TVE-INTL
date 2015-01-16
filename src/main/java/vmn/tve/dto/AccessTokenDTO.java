package vmn.tve.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * AccessTokenDTO object converted to json and returned to centralized service.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenDTO extends ResponseDTO {
    private String mvpdId = "";
    private String accessToken = "";
    private String accessTokenTTL = "";
    private String refreshToken = "";
    private String refreshTokenTTL = "";
    private String state = "";

    public String getMvpdId() {
        return mvpdId;
    }

    public void setMvpdId(final String mvpdId) {
        this.mvpdId = mvpdId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenTTL() {
        return accessTokenTTL;
    }

    public void setAccessTokenTTL(final String accessTokenTTL) {
        this.accessTokenTTL = accessTokenTTL;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenTTL() {
        return refreshTokenTTL;
    }

    public void setRefreshTokenTTL(final String refreshTokenTTL) {
        this.refreshTokenTTL = refreshTokenTTL;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }
}
