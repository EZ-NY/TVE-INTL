package vmn.tve.services.oauth.request.builders;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * RequestBuilderRefreshToken - builds request to get refresh token information.
 */
@Component
public class RequestBuilderRefreshToken extends RequestBuilderToken {

    @Override
    public MultiValueMap<String, String> buildBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add(REFRESH_TOKEN, getRequest().getRefreshToken());
        return body;
    }

    @Override
    public String getGrantType() {
        return GRANT_TYPE_REFRESH_TOKEN;
    }
}
