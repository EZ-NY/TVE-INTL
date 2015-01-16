package vmn.tve.services.oauth.request.builders;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestBuilderAccessTokenTest {

    private final static String URL_ACCESS_TOKEN = "https://tyr-test.apigee.net/v1/dev/accesstoken?client_id=pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG&grant_type=authorization_code";

    @InjectMocks
    private RequestBuilderAccessToken builder;

    @Mock
    private OAuthRequest request;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(request.getPath()).thenReturn("https://tyr-test.apigee.net/v1/dev/accesstoken");
        when(request.getClientId()).thenReturn("pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG");
        when(request.getClientSecret()).thenReturn("3JFo0B4XYCDMEXbC");
        when(request.getAuthorizationCode()).thenReturn("BP35AEMG");
        when(request.getRedirectUri()).thenReturn("http://localhost/");
    }

    @Test
    public void testBuildUrl() {
        builder.setRequest(request);
        String url = builder.buildUrl();
        Assert.assertNotNull(url);
        Assert.assertEquals(url, URL_ACCESS_TOKEN);
    }

    @Test
    public void testBuildBody() {
        MultiValueMap<String, String> body = builder.buildBody();
        Assert.assertNotNull(body);
        Assert.assertNotNull(body.get(RequestBuilder.AUTHORIZATION_CODE));
        Assert.assertEquals(body.get(RequestBuilder.AUTHORIZATION_CODE).get(0), "BP35AEMG");
        Assert.assertNotNull(body.get(RequestBuilder.REDIRECT_URI));
        Assert.assertEquals(body.get(RequestBuilder.REDIRECT_URI).get(0), "http://localhost/");
    }

    @Test
    public void testBuildHeaders() {
        HttpHeaders headers = builder.buildHeaders();
        Assert.assertNotNull(headers.getContentType());
        Assert.assertEquals(headers.getContentType(), MediaType.APPLICATION_FORM_URLENCODED);
        Assert.assertNotNull(headers.get(RequestBuilder.AUTHORIZATION));
        Assert.assertNotNull(headers.get(RequestBuilder.AUTHORIZATION).get(0),
                "cGJHekdybVJXQXRjVkZMSnFRVThEaklhbkFwMllNQkc6QlAzNUFFTUc=");
    }
}
