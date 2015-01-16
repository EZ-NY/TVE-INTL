package vmn.tve.services.oauth.request.builders;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * RequestBuilderAccessToken - builds request to get access token information.
 */
@Component
public class RequestBuilderAccessToken extends RequestBuilderToken {

    @Override
    public MultiValueMap<String, String> buildBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add(AUTHORIZATION_CODE, getRequest().getAuthorizationCode());
        body.add(REDIRECT_URI, getRequest().getRedirectUri());
        return body;
    }

    @Override
    public String getGrantType() {
        return GRANT_TYPE_AUTH_CODE;
    }
}
