package vmn.tve.providers.nos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccessTokenInfo object returned from provider.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenInfo {

    @JsonProperty("application_name")
    private String applicationName;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("refresh_token_issued_at")
    private String refreshTokenIssuedAt;

    @JsonProperty("refresh_token_status")
    private String refreshTokenStatus;

    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_count")
    private String refreshCount;

    @JsonProperty("issued_at")
    private String issuedAt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("api_product_list")
    private String apiProductList;

    @JsonProperty("developer.email")
    private String developerEmail;

    @JsonProperty("organization_id")
    private String organizationId;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("refresh_token_expires_in")
    private String refreshTokenExpiresIn;

    @JsonProperty("organization_name")
    private String organizationName;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_ca")
    private String userCa;

    @JsonProperty("user_sa")
    private String userSA;

    @JsonProperty("user_session_id")
    private String userSessionId;

    @JsonProperty("user_display_name")
    private String userDisplayName;

    @JsonProperty("user_type")
    private String userType;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }

    public String getRefreshTokenIssuedAt() {
        return refreshTokenIssuedAt;
    }

    public void setRefreshTokenIssuedAt(final String refreshTokenIssuedAt) {
        this.refreshTokenIssuedAt = refreshTokenIssuedAt;
    }

    public String getRefreshTokenStatus() {
        return refreshTokenStatus;
    }

    public void setRefreshTokenStatus(final String refreshTokenStatus) {
        this.refreshTokenStatus = refreshTokenStatus;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(final String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(final String refreshCount) {
        this.refreshCount = refreshCount;
    }

    public String getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(final String issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getApiProductList() {
        return apiProductList;
    }

    public void setApiProductList(final String apiProductList) {
        this.apiProductList = apiProductList;
    }

    public String getDeveloperEmail() {
        return developerEmail;
    }

    public void setDeveloperEmail(final String developerEmail) {
        this.developerEmail = developerEmail;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(final String organizationId) {
        this.organizationId = organizationId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(final String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(final String refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(final String organizationName) {
        this.organizationName = organizationName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getUserCa() {
        return userCa;
    }

    public void setUserCa(final String userCa) {
        this.userCa = userCa;
    }

    public String getUserSA() {
        return userSA;
    }

    public void setUserSA(final String userSA) {
        this.userSA = userSA;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(final String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(final String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(final String userType) {
        this.userType = userType;
    }

}
