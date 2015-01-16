package vmn.tve.providers.nos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import vmn.tve.providers.DataProvider;
import vmn.tve.services.oauth.request.builders.OAuthRequest;
import vmn.tve.services.oauth.request.builders.RequestBuilder;
import vmn.tve.services.request.IncomingRequest;
import vmn.tve.services.request.IncomingRequestService;
import vmn.tve.utils.Constants;

/**
 * DataProviderImpl object intended to gather configuration information that
 * will be passed to provider.
 */
@Service
public class DataProviderImpl implements DataProvider {

    @Autowired
    private IncomingRequestService irs;

    @Autowired
    private Environment env;

    @Override
    public OAuthRequest loadLoginData() {
        IncomingRequest rd = irs.getIncomingRequest();
        String method = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + Constants.METHOD_LOGIN);
        String scope = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + RequestBuilder.SCOPE);
        OAuthRequest request = new OAuthRequest();
        request.setLoginOrLogoutRequest(true);
        request.setClientId(loadClientId(rd));
        request.setEndpoint(loadEndpoint(rd));
        request.setMethod(method);
        request.setPath(request.getEndpoint() + request.getMethod());
        request.setScope(scope);
        request.setState(rd.getState());
        request.setRedirectUri(rd.getRedirectUri());
        request.setImplicitGrantFlow(rd.isImplicitGrantFlow());
        return request;
    }

    @Override
    public OAuthRequest loadLogoutData() {
        IncomingRequest rd = irs.getIncomingRequest();
        String method = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + Constants.METHOD_LOGOUT);
        OAuthRequest request = new OAuthRequest();
        request.setLoginOrLogoutRequest(true);
        request.setClientId(loadClientId(rd));
        request.setEndpoint(loadEndpoint(rd));
        request.setMethod(method);
        request.setPath(request.getEndpoint() + request.getMethod());
        request.setAccessToken(rd.getAccessToken());
        request.setRedirectUri(rd.getRedirectUri());
        return request;
    }

    @Override
    public OAuthRequest loadAccessTokenData() {
        IncomingRequest rd = irs.getIncomingRequest();
        String method = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT
                + Constants.METHOD_GET_ACCESS_TOKEN);
        OAuthRequest request = new OAuthRequest();
        request.setClientId(loadClientId(rd));
        request.setClientSecret(loadClientSecret(rd));
        request.setEndpoint(loadEndpoint(rd));
        request.setMethod(method);
        request.setPath(request.getEndpoint() + request.getMethod());
        request.setAuthorizationCode(rd.getAuthorizationCode());
        request.setRedirectUri(rd.getRedirectUri());
        return request;
    }

    @Override
    public OAuthRequest loadValidateTokenData() {
        IncomingRequest rd = irs.getIncomingRequest();
        String method = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + Constants.METHOD_VALIDATE_TOKEN);
        OAuthRequest request = new OAuthRequest();
        request.setClientId(loadClientId(rd));
        request.setEndpoint(loadEndpoint(rd));
        request.setMethod(method);
        request.setPath(request.getEndpoint() + request.getMethod());
        request.setAccessToken(rd.getAccessToken());
        return request;
    }

    @Override
    public OAuthRequest loadRefreshTokenData() {
        IncomingRequest rd = irs.getIncomingRequest();
        String method = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + Constants.METHOD_REFRESH_TOKEN);
        OAuthRequest request = new OAuthRequest();
        request.setClientId(loadClientId(rd));
        request.setClientSecret(loadClientSecret(rd));
        request.setEndpoint(loadEndpoint(rd));
        request.setMethod(method);
        request.setPath(request.getEndpoint() + request.getMethod());
        request.setRefreshToken(rd.getRefreshToken());
        return request;
    }

    @Override
    public OAuthRequest loadCheckPermissionsData() {
        IncomingRequest rd = irs.getIncomingRequest();
        String endpoint = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT
                + Constants.RESOURCES_ENDPOINT_URL);
        String channelId = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + Constants.PROVIDER_CHANNEL_ID
                + Constants.DOT + rd.getResourceId());
        String method = env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT
                + Constants.METHOD_CHECK_PERMISSIONS);
        OAuthRequest request = new OAuthRequest();
        request.setClientId(loadClientId(rd));
        request.setEndpoint(endpoint);
        request.setMethod(method);
        request.setChannelId(channelId);
        request.setPath(request.getEndpoint() + request.getMethod().replace("{channelId}", channelId));
        request.setAccessToken(rd.getAccessToken());
        return request;
    }

    private String loadEndpoint(final IncomingRequest rd) {
        return env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + Constants.OAUTH_ENDPOINT_URL);
    }

    private String loadClientId(final IncomingRequest rd) {
        return env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + rd.getComponentId() + Constants.DOT
                + rd.getRequestorId() + Constants.DOT + RequestBuilder.CLIENT_ID);
    }

    private String loadClientSecret(final IncomingRequest rd) {
        return env.getProperty(rd.getMvpdId().toLowerCase() + Constants.DOT + rd.getComponentId() + Constants.DOT
                + rd.getRequestorId() + Constants.DOT + RequestBuilder.CLIENT_SECRET);
    }
}
