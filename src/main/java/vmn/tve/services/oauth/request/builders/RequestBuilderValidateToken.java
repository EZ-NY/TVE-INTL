package vmn.tve.services.oauth.request.builders;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

/**
 * RequestBuilderValidateToken - builds request to get access token validation
 * information.
 */
@Component
public class RequestBuilderValidateToken extends RequestBuilder {

    @Override
    public String buildUrl() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(getRequest().getPath());
        uriBuilder.setParameter(CLIENT_ID, getRequest().getClientId());
        uriBuilder.setParameter(ACCESS_TOKEN, getRequest().getAccessToken());
        return uriBuilder.toString();
    }
}
