package vmn.tve.services.oauth.request.builders;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 * RequestBuilderToken - builds general request to get access or refresh token
 * information.
 */
public abstract class RequestBuilderToken extends RequestBuilder {

    @Override
    public String buildUrl() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(getRequest().getPath());
        uriBuilder.setParameter(CLIENT_ID, getRequest().getClientId());
        uriBuilder.setParameter(GRANT_TYPE, getGrantType());
        return uriBuilder.toString();
    }

    @Override
    public HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String auth = getRequest().getClientId() + ":" + getRequest().getClientSecret();
        headers.set(AUTHORIZATION, Base64.encodeBase64String(auth.getBytes()));
        return headers;
    }

    @Override
    public HttpMethod buildMethod() {
        return HttpMethod.POST;
    }

    /**
     * @return specific grant type - access toke or refresh token defined in
     *         derived classes
     */
    public abstract String getGrantType();
}
