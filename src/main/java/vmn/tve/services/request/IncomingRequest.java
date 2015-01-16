package vmn.tve.services.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.errors.ResponseCode;
import vmn.tve.services.errors.ResponseGroup;
import vmn.tve.utils.Constants;

/**
 * Class created to retain all information about incoming request to local
 * service. Objects of this class reside only in scope to handle request.
 */
@Component
@Scope(value = "request")
public class IncomingRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IncomingRequest.class);

    private boolean isLoginRequest;
    private String mvpdId;
    private String componentId;
    private String requestorId;
    private String locale;
    private String deviceType;
    private String state;
    private String redirectUri;
    private String resourceId;
    private String accessToken;
    private String authorizationCode;
    private String method;
    private String clientId;
    private String refreshToken;
    private boolean isImplicitGrantFlow;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String providerServiceManager;

    public String getMvpdId() {
        return mvpdId;
    }

    public void setMvpdId(final String mvpdId) {
        this.mvpdId = mvpdId;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(final String componentId) {
        this.componentId = componentId;
    }

    public String getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(final String requestorId) {
        this.requestorId = requestorId;
    }

    public String getLocale() {
        return locale;
    }

    /**
     * @param locale - set incoming locale parameter and validate it.
     */
    public void setLocale(final String locale) {
        if (locale == null || locale.isEmpty() || !locale.matches("\\w{2}_\\w{2}")) {
            LOGGER.error(Constants.MSG_INCORRECT_LOCALE);
            if (isLoginRequest) {
                throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_REQUEST, ResponseCode.INVALID_LOCALE);
            } else {
                throw new LocalServiceException(ResponseGroup.PROVIDER_BAD_REQUEST_ERROR, ResponseCode.INVALID_LOCALE,
                        HttpStatus.BAD_REQUEST);
            }
        }
        this.locale = locale;
    }

    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType - set incomming device type parameter and validate it.
     */
    public void setDeviceType(final String deviceType) {
        if (deviceType == null || deviceType.isEmpty()) {
            LOGGER.error(Constants.MSG_DEVICE_TYPE_EMPTY);
            if (isLoginRequest) {
                throw new LocalServiceException(ResponseGroup.LOGIN_INVALID_REQUEST,
                        ResponseCode.LOCAL_SERVICE_CONFIGURATION_ERROR);
            } else {
                throw new LocalServiceException(ResponseGroup.PROVIDER_BAD_REQUEST_ERROR,
                        ResponseCode.DEVICE_TYPE_ID_EMPTY, HttpStatus.BAD_REQUEST);
            }
        }
        this.deviceType = deviceType;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(final String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(final HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(final HttpServletResponse response) {
        this.response = response;
    }

    public String getProviderServiceManager() {
        return providerServiceManager;
    }

    public void setProviderServiceManager(final String providerServiceManager) {
        this.providerServiceManager = providerServiceManager;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(final String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isImplicitGrantFlow() {
        return isImplicitGrantFlow;
    }

    public void setImplicitGrantFlow(final boolean isImplicit) {
        this.isImplicitGrantFlow = isImplicit;
    }

    public boolean isLoginRequest() {
        return isLoginRequest;
    }

    public void setLoginRequest(final boolean isLogin) {
        this.isLoginRequest = isLogin;
    }
}
