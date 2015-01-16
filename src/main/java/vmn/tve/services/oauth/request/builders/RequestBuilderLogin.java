package vmn.tve.services.oauth.request.builders;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

/**
 * RequestBuilderLogin - builds request for login send redirect flow.
 */
@Component
public class RequestBuilderLogin extends RequestBuilder {

    @Override
    public String buildUrl() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(getRequest().getPath());
        uriBuilder.setParameter(CLIENT_ID, getRequest().getClientId());
        if (getRequest().isImplicitGrantFlow()) {
            uriBuilder.setParameter(RESPONSE_TYPE, RESPONSE_TYPE_TOKEN);
        } else {
            uriBuilder.setParameter(RESPONSE_TYPE, RESPONSE_TYPE_CODE);
        }
        uriBuilder.setParameter(SCOPE, getRequest().getScope());
        uriBuilder.setParameter(STATE, getRequest().getState());
        uriBuilder.setParameter(REDIRECT_URI, getRequest().getRedirectUri());
        return uriBuilder.toString();
    }
}
