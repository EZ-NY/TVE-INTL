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

public class RequestBuilderRefreshTokenTest {

    private final static String URL_REFRESH_TOKEN = "https://tyr-test.apigee.net/v1/dev/refreshtoken?client_id=pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG&grant_type=refresh_token";

    @InjectMocks
    private RequestBuilderRefreshToken builder;

    @Mock
    private OAuthRequest request;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(request.getPath()).thenReturn("https://tyr-test.apigee.net/v1/dev/refreshtoken");
        when(request.getClientId()).thenReturn("pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG");
        when(request.getClientSecret()).thenReturn("3JFo0B4XYCDMEXbC");
        when(request.getRefreshToken()).thenReturn("UfUntBU3e99TI6GMDWVWj8GoEhwGx4yy");

    }

    @Test
    public void testBuildUrl() {
        builder.setRequest(request);
        String url = builder.buildUrl();
        Assert.assertNotNull(url);
        Assert.assertEquals(url, URL_REFRESH_TOKEN);
    }

    @Test
    public void testBuildBody() {
        MultiValueMap<String, String> body = builder.buildBody();
        Assert.assertNotNull(body);
        Assert.assertNotNull(body.get(RequestBuilder.REFRESH_TOKEN));
        Assert.assertEquals(body.get(RequestBuilder.REFRESH_TOKEN).get(0), "UfUntBU3e99TI6GMDWVWj8GoEhwGx4yy");
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
