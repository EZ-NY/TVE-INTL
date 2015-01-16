package vmn.tve.services.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * RequestDataService intende3d to save all incoming parameters to one object
 * that will reside during request. This RequestData object can be accessible in
 * any part of app to avoid passing all request parameters in methods.
 */
@Service
public class IncomingRequestService {

    @Autowired
    private ApplicationContext context;

    public IncomingRequest getIncomingRequest() {
        return context.getBean(IncomingRequest.class);
    }

    /**
     * @param isLoginRequest - incoming request is related to login flow
     * @param componentId - incoming platform parameter
     * @param requestorId - incoming brand parameter
     * @param locale - incoming locale in format lang_countryCode
     * @param deviceType - incoming device type incoming
     * @param state - incoming state parameter used to protect against forgery
     *            attack
     * @param mvpdId - incoming provider id parameter
     * @param redirectUri - incoming redirect uri parameter
     * @param accessToken - incoming access token parameter
     * @param authorizationCode - incoming authorization code parameter
     * @param refreshToken - incoming refresh token parameter
     * @param resourceId - incoming resource id parameter to get channel id
     * @param req - incoming request parameter
     * @param res - incoming response parameter
     */
    public void setIncomingRequest(final boolean isLoginRequest, final String componentId, final String requestorId,
            final String locale, final String deviceType, final String state, final String mvpdId,
            final String redirectUri, final String accessToken, final String authorizationCode,
            final String refreshToken, final String resourceId, final HttpServletRequest req,
            final HttpServletResponse res) {
        IncomingRequest rd = getIncomingRequest();
        rd.setLoginRequest(isLoginRequest);
        rd.setRedirectUri(redirectUri);
        rd.setMvpdId(mvpdId);
        rd.setLocale(locale);
        rd.setComponentId(componentId);
        rd.setRequestorId(requestorId);
        rd.setDeviceType(deviceType);
        rd.setState(state);
        rd.setAccessToken(accessToken);
        rd.setAuthorizationCode(authorizationCode);
        rd.setRefreshToken(refreshToken);
        rd.setResourceId(resourceId);
        rd.setRequest(req);
        rd.setResponse(res);
    }

}
