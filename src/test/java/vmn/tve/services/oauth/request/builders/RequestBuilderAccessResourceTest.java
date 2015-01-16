package vmn.tve.services.oauth.request.builders;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestBuilderAccessResourceTest {

    private final static String URL_CHECK_PERMISSIONS = "https://tyr-test.apigee.net/v1/dev/core/Users/me/SubscribedChannels('94')?"
            + "client_id=pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG&access_token=jqYJWVPiWa1XmXauuo1RbH5cRr3K&%24format=json";

    @InjectMocks
    private RequestBuilderAccessResource builder;

    @Mock
    private OAuthRequest request;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(request.getPath()).thenReturn("https://tyr-test.apigee.net/v1/dev/core/Users/me/SubscribedChannels('94')");
        when(request.getClientId()).thenReturn("pbGzGrmRWAtcVFLJqQU8DjIanAp2YMBG");
        when(request.getAccessToken()).thenReturn("jqYJWVPiWa1XmXauuo1RbH5cRr3K");
    }

    @Test
    public void testBuildUrl() {
        builder.setRequest(request);
        String url = builder.buildUrl();
        Assert.assertNotNull(url);
        Assert.assertEquals(url, URL_CHECK_PERMISSIONS);
    }
}
