package vmn.tve.services.oauth.request.builders;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import vmn.tve.utils.Constants;

/**
 * RequestBuilderAccessResource - builds request to access resource.
 */
@Component
public class RequestBuilderAccessResource extends RequestBuilder {
    @Override
    public String buildUrl() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(getRequest().getPath());
        uriBuilder.setParameter(CLIENT_ID, getRequest().getClientId());
        uriBuilder.setParameter(ACCESS_TOKEN, getRequest().getAccessToken());
        uriBuilder.setParameter(Constants.FORMAT, Constants.FORMAT_JSON);
        return uriBuilder.toString();
    }
}
