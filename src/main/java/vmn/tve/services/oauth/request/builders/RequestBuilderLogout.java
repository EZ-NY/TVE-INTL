package vmn.tve.services.oauth.request.builders;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import vmn.tve.utils.Constants;

/**
 * RequestBuilderLogout - builds request for logout send redirect flow.
 */
@Component
public class RequestBuilderLogout extends RequestBuilder {

    @Override
    public String buildUrl() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(getRequest().getPath());
        uriBuilder.setParameter(CLIENT_ID, getRequest().getClientId());
        uriBuilder.setParameter(ACCESS_TOKEN, getRequest().getAccessToken());
        uriBuilder.setParameter(Constants.FORCE_LOGOUT, "true");
        uriBuilder.setParameter(REDIRECT_URI, Base64.encodeBase64String(getRequest().getRedirectUri().getBytes()));
        return uriBuilder.toString();
    }
}
