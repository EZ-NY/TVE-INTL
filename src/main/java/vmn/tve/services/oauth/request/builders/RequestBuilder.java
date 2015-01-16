package vmn.tve.services.oauth.request.builders;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;

/**
 * RequestBuilder has to be implemented to populate and build requests to
 * provider.
 */
public abstract class RequestBuilder {
    public static final String AUTHORIZATION_CODE = "code";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String AUTHORIZATION = "Authorization";
    public static final String SCOPE = "scope";
    public static final String GRANT_TYPE = "grant_type";
    public static final String GRANT_TYPE_AUTH_CODE = "authorization_code";
    public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    public static final String REFRESH_TOKEN = GRANT_TYPE_REFRESH_TOKEN;
    public static final String ACCESS_TOKEN = "access_token";
    public static final String RESPONSE_TYPE = "response_type";
    public static final String RESPONSE_TYPE_CODE = AUTHORIZATION_CODE;
    public static final String RESPONSE_TYPE_TOKEN = "token";
    public static final String STATE = "state";

    private OAuthRequest request;

    public OAuthRequest getRequest() {
        return request;
    }

    public void setRequest(final OAuthRequest request) {
        this.request = request;
    }

    /**
     * @return full provider url
     */
    public abstract String buildUrl();

    /**
     * @return headers created for appropriate method call.
     */
    public HttpHeaders buildHeaders() {
        return new HttpHeaders();
    }

    /**
     * @return body created for appropriate method call
     */
    public Object buildBody() {
        return new LinkedMultiValueMap<String, String>();
    }

    /**
     * @return method that is used for appropriate method call
     */
    public HttpMethod buildMethod() {
        return HttpMethod.GET;
    }
}
