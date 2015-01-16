package vmn.tve.services.oauth.request.builders;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestBuilderLoginTest {
    private final static String URL_LOGIN = "https://tyr-test.apigee.net/v1/dev/oauth2/authorizeapp?"
            + "client_id=pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG&response_type=code&scope=user_profile+user_tv_channels_portfolio&state=mystate&redirect_uri=http%3A%2F%2Flocalhost";

    @InjectMocks
    private RequestBuilderLogin builder;

    @Mock
    private OAuthRequest request;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(request.getPath()).thenReturn("https://tyr-test.apigee.net/v1/dev/oauth2/authorizeapp");
        when(request.getClientId()).thenReturn("pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG");
        when(request.getAccessToken()).thenReturn("jqYJWVPiWa1XmXauuo1RbH5cRr3K");
        when(request.isImplicitGrantFlow()).thenReturn(false);
        when(request.getScope()).thenReturn("user_profile user_tv_channels_portfolio");
        when(request.getState()).thenReturn("mystate");
        when(request.getRedirectUri()).thenReturn("http://localhost");
    }

    @Test
    public void testBuildUrl() {
        builder.setRequest(request);
        String url = builder.buildUrl();
        Assert.assertNotNull(url);
        Assert.assertEquals(url, URL_LOGIN);
    }
}
