package vmn.tve.services.oauth.request.builders;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestBuilderValidateTokenTest {

    private final static String URL_VALIDATE_TOKEN = "https://tyr-test.apigee.net/v1/dev/tokeninfo?"
            + "client_id=pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG&access_token=UfUntBU3e99TI6GMDWVWj8GoEhwGx4yy";

    @InjectMocks
    private RequestBuilderValidateToken builder;

    @Mock
    private OAuthRequest request;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(request.getPath()).thenReturn("https://tyr-test.apigee.net/v1/dev/tokeninfo");
        when(request.getClientId()).thenReturn("pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG");
        when(request.getAccessToken()).thenReturn("UfUntBU3e99TI6GMDWVWj8GoEhwGx4yy");
    }

    @Test
    public void testBuildUrl() {
        builder.setRequest(request);
        String url = builder.buildUrl();
        Assert.assertNotNull(url);
        Assert.assertEquals(url, URL_VALIDATE_TOKEN);
    }

}
