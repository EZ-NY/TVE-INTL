package vmn.tve.services.oauth.request.builders;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.errors.ResponseCode;
import vmn.tve.services.errors.ResponseGroup;
import vmn.tve.utils.Constants;

/**
 * OAuthRequest to populate with all necessary configuration information that
 * will be sent to provider.
 */
public class OAuthRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthRequest.class);

    private String path;
    private String endpoint;
    private String method;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String redirectUri;
    private String authorizationCode;
    private String accessToken;
    private String refreshToken;
    private String state;
    private String body;
    private String channelId;
    private boolean isImplicitGrantFlow;
    private boolean isLoginOrLogoutRequest;
    private HttpServletResponse response;

    /**
     * @param path - full provider uri
     */
    public void setPath(final String path) {
        if (path == null || path.isEmpty()) {
            LOGGER.error(Constants.MSG_ENDPOINT_URL_EMPTY);
            if (isLoginOrLogoutRequest()) {
                throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_REQUEST,
                        ResponseCode.LOCAL_SERVICE_CONFIGURATION_ERROR);
            } else {
                throw new LocalServiceException(ResponseGroup.LOCAL_SERVICE_CONFIGURATION_ERROR,
                        ResponseCode.ENDPOINT_URL_EMPTY, HttpStatus.NOT_FOUND);
            }
        }
        this.path = path;
    }

    /**
     * @param endpoint - provider endpoint without method
     */
    public void setEndpoint(final String endpoint) {
        if (endpoint == null || endpoint.isEmpty()) {
            LOGGER.error(Constants.MSG_ENDPOINT_URL_EMPTY);
            if (isLoginOrLogoutRequest()) {
                throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_REQUEST,
                        ResponseCode.LOCAL_SERVICE_CONFIGURATION_ERROR);
            } else {
                throw new LocalServiceException(ResponseGroup.LOCAL_SERVICE_CONFIGURATION_ERROR,
                        ResponseCode.ENDPOINT_URL_EMPTY, HttpStatus.NOT_FOUND);
            }
        }
        this.endpoint = endpoint;
    }

    /**
     * @param method - provider uri method
     */
    public void setMethod(final String method) {
        if (method == null || method.isEmpty()) {
            LOGGER.error(Constants.MSG_METHOD_URL_EMPTY);
            if (isLoginOrLogoutRequest()) {
                throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_REQUEST,
                        ResponseCode.LOCAL_SERVICE_CONFIGURATION_ERROR);
            } else {
                throw new LocalServiceException(ResponseGroup.LOCAL_SERVICE_CONFIGURATION_ERROR,
                        ResponseCode.ENDPOINT_METHOD_EMPTY, HttpStatus.NOT_FOUND);
            }
        }
        this.method = method;
    }

    /**
     * @param clientId - client_id configured in local service
     */
    public void setClientId(final String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            LOGGER.error(Constants.MSG_CLIENT_ID_EMPTY);
            if (isLoginOrLogoutRequest()) {
                throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_REQUEST,
                        ResponseCode.LOCAL_SERVICE_CONFIGURATION_ERROR);
            } else {
                throw new LocalServiceException(ResponseGroup.LOCAL_SERVICE_CONFIGURATION_ERROR,
                        ResponseCode.CLIENT_ID_EMPTY, HttpStatus.NOT_FOUND);
            }
        }
        this.clientId = clientId;
    }

    /**
     * @param clientSecret - client_secret configured in local service
     */
    public void setClientSecret(final String clientSecret) {
        if (clientSecret == null || clientSecret.isEmpty()) {
            LOGGER.error(Constants.MSG_CLIENT_SECRET_EMPTY);
            throw new LocalServiceException(ResponseGroup.LOCAL_SERVICE_CONFIGURATION_ERROR,
                    ResponseCode.CLIENT_SECRET_EMPTY, HttpStatus.NOT_FOUND);
        }
        this.clientSecret = clientSecret;
    }

    /**
     * @param scope - scope configured in local service
     */
    public void setScope(final String scope) {
        if (scope == null || scope.isEmpty()) {
            LOGGER.error(Constants.MSG_SCOPE_EMPTY);
            throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_SCOPE,
                    ResponseCode.LOCAL_SERVICE_CONFIGURATION_ERROR);
        }
        this.scope = scope;
    }

    /**
     * @param state - state received from centralized service
     */
    public void setState(final String state) {
        if (state == null || state.isEmpty()) {
            LOGGER.error(Constants.MSG_STATE_EMPTY);
            throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_REQUEST,
                    ResponseCode.LOCAL_SERVICE_CONFIGURATION_ERROR);
        }
        this.state = state;
    }

    /**
     * @param redirectUri - redirectUri received from centralized service
     */
    public void setRedirectUri(final String redirectUri) {
        if (redirectUri == null || redirectUri.isEmpty()) {
            LOGGER.error(Constants.MSG_REDIRECT_URI_EMPTY);
            if (isLoginOrLogoutRequest()) {
                throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_REQUEST, ResponseCode.REDIRECT_URI_EMPTY);
            } else {
                throw new LocalServiceException(ResponseGroup.PROVIDER_BAD_REQUEST_ERROR,
                        ResponseCode.REDIRECT_URI_EMPTY, HttpStatus.BAD_REQUEST);
            }
        }
        this.redirectUri = redirectUri;
    }

    /**
     * @param authorizationCode - authorization code received from centralized
     *            service
     */
    public void setAuthorizationCode(final String authorizationCode) {
        if (authorizationCode == null || authorizationCode.isEmpty()) {
            LOGGER.error(Constants.MSG_AUTH_CODE_EMPTY);
            throw new LocalServiceException(ResponseGroup.AUTHORIZATION_ERROR, ResponseCode.AUTHORIZATION_CODE_EMPTY,
                    HttpStatus.UNAUTHORIZED);
        }
        this.authorizationCode = authorizationCode;
    }

    /**
     * @param accessToken - access token received from centralized service
     */
    public void setAccessToken(final String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            LOGGER.error(Constants.MSG_ACCESS_TOKEN_EMPTY);
            throw new LocalServiceException(ResponseGroup.AUTHORIZATION_ERROR, ResponseCode.ACCESS_TOKEN_EMPTY,
                    HttpStatus.UNAUTHORIZED);
        }
        this.accessToken = accessToken;
    }

    /**
     * @param refreshToken - refresh token received from centralized service
     */
    public void setRefreshToken(final String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            LOGGER.error(Constants.MSG_REFRESH_TOKEN_EMPTY);
            throw new LocalServiceException(ResponseGroup.AUTHORIZATION_ERROR, ResponseCode.REFRESH_TOKEN_EMPTY,
                    HttpStatus.UNAUTHORIZED);
        }
        this.refreshToken = refreshToken;
    }

    /**
     * @param channelId - channel id configured in local service
     */
    public void setChannelId(final String channelId) {
        if (channelId == null || channelId.isEmpty()) {
            LOGGER.error(Constants.MSG_CHANNEL_ID_EMPTY);
            throw new LocalServiceException(ResponseGroup.LOCAL_SERVICE_CONFIGURATION_ERROR,
                    ResponseCode.CHANNEL_ID_EMPTY, HttpStatus.NOT_FOUND);
        }
        this.channelId = channelId;
    }

    public String getPath() {
        return path;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getState() {
        return state;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(final HttpServletResponse response) {
        this.response = response;
    }

    public boolean isImplicitGrantFlow() {
        return isImplicitGrantFlow;
    }

    public void setImplicitGrantFlow(final boolean isImplicit) {
        this.isImplicitGrantFlow = isImplicit;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getMethod() {
        return method;
    }

    public String getChannelId() {
        return channelId;
    }

    public boolean isLoginOrLogoutRequest() {
        return isLoginOrLogoutRequest;
    }

    public void setLoginOrLogoutRequest(final boolean isLoginOrLogout) {
        this.isLoginOrLogoutRequest = isLoginOrLogout;
    }
}
