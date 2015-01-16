package vmn.tve.providers.nos;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.request.IncomingRequest;
import vmn.tve.services.request.IncomingRequestService;

/*
 * Testing responses received from NOS provider.
 */
public class ResponseParserTest {
    private static final String SUCCESSFUL_ACCESS_TOKEN_RESPONSE = "successful_access_token_response.txt";
    private static final String SUCCESSFUL_VALIDATE_TOKEN_RESPONSE = "successful_validate_token_response.txt";
    private static final String SUCCESSFUL_REFRESH_TOKEN_RESPONSE = "successful_refresh_token_response.txt";
    private static final String CHECK_PERMISSIONS_AUTHORIZED_RESPONSE = "successful_check_permissions_response.txt";
    private static final String CHECK_PERMISSIONS_NOT_AUTHORIZED_RESPONSE = "error_check_permissions_response.txt";

    @InjectMocks
    private ResponseParserImpl responseParser;

    @Mock
    private IncomingRequestService rds;

    @Mock
    private IncomingRequest incomingRequest;

    @Mock
    private ResponseEntity<String> responseEntity;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(rds.getIncomingRequest()).thenReturn(incomingRequest);
        when(incomingRequest.getMvpdId()).thenReturn("NOS");
        when(incomingRequest.getState()).thenReturn("mystate");
        when(incomingRequest.getResourceId()).thenReturn("nick");
    }

    @Test
    public void testAccessTokenResponseToCS() {
        try {
            String expectedResponse = "{\"status\":\"success\",\"function\":\"getAccessToken\",\"mvpdId\":\"NOS\","
                    + "\"accessToken\":\"pyo1WEJLNA8jLnBHsU2huhFyB2HU\",\"accessTokenTTL\":\"10799\","
                    + "\"refreshToken\":\"OQ9UUOlq8V83j5TFUIuGxXthHK8uZkuF\",\"refreshTokenTTL\":\"0\",\"state\":\"mystate\"}";
            when(rds.getIncomingRequest().getMvpdId()).thenReturn("NOS");
            when(rds.getIncomingRequest().getState()).thenReturn("mystate");
            when(responseEntity.getBody()).thenReturn(getJSONString(SUCCESSFUL_ACCESS_TOKEN_RESPONSE));
            responseParser.setResponse(responseEntity);
            String parsedResponseToCS = responseParser.parseAccessToken();
            Assert.assertNotNull(parsedResponseToCS);
            Assert.assertEquals(expectedResponse, parsedResponseToCS);
        } catch (IOException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateTokenResponseToCS() {
        try {
            String expectedResponse = "{\"status\":\"success\",\"function\":\"validateToken\",\"user_id\":\"nickelodeon.user1\","
                    + "\"user_sa\":\"S123456789\",\"user_display_name\":\"nickelodeon.user1\","
                    + "\"user_session_id\":\"ggwmzudtbhwtgaz2b45rcgog\",\"user_type\":\"user\"}";
            when(responseEntity.getBody()).thenReturn(getJSONString(SUCCESSFUL_VALIDATE_TOKEN_RESPONSE));
            responseParser.setResponse(responseEntity);
            String parsedResponseToCS = responseParser.parseValidateToken();
            Assert.assertNotNull(parsedResponseToCS);
            Assert.assertEquals(expectedResponse, parsedResponseToCS);
        } catch (IOException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testRefreshTokenResponseToCS() {
        try {
            String expectedResponse = "{\"status\":\"success\",\"function\":\"refreshToken\",\"mvpdId\":\"NOS\","
                    + "\"accessToken\":\"CqWlIR4fi9Fm74IIzFR77Xoqdoih\",\"accessTokenTTL\":\"1799\","
                    + "\"refreshToken\":\"41dIBzDo9x3vZeeFpc3pkBW34BAuHDhv\",\"refreshTokenTTL\":\"0\",\"state\":\"mystate\"}";
            when(responseEntity.getBody()).thenReturn(getJSONString(SUCCESSFUL_REFRESH_TOKEN_RESPONSE));
            responseParser.setResponse(responseEntity);
            String parsedResponseToCS = responseParser.parseRefreshToken();
            Assert.assertNotNull(parsedResponseToCS);
            Assert.assertEquals(expectedResponse, parsedResponseToCS);
        } catch (IOException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckPermissionsAuthorizedResponseToCS() {
        try {
            String expectedResponse = "{\"status\":\"success\",\"function\":\"checkPermissions\",\"authZStatus\":1,"
                    + "\"state\":\"mystate\",\"mvpdId\":\"NOS\",\"resourceId\":\"nick\"}";
            when(responseEntity.getBody()).thenReturn(getJSONString(CHECK_PERMISSIONS_AUTHORIZED_RESPONSE));
            responseParser.setResponse(responseEntity);
            String parsedResponseToCS = responseParser.parseResource();
            Assert.assertNotNull(parsedResponseToCS);
            Assert.assertEquals(expectedResponse, parsedResponseToCS);
        } catch (IOException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckPermissionsNotAuthorizedResponseToCS() {
        try {
            String expectedResponse = "{\"status\":\"success\",\"function\":\"checkPermissions\",\"authZStatus\":0,"
                    + "\"state\":\"mystate\",\"mvpdId\":\"NOS\",\"resourceId\":\"nick\"}";
            when(responseEntity.getBody()).thenReturn(getJSONString(CHECK_PERMISSIONS_NOT_AUTHORIZED_RESPONSE));
            responseParser.setResponse(responseEntity);
            String parsedResponseToCS = responseParser.parseResource();
            Assert.assertNotNull(parsedResponseToCS);
            Assert.assertEquals(expectedResponse, parsedResponseToCS);
        } catch (IOException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = LocalServiceException.class)
    public void testUnrecognizedProviderResponse() {
        when(responseEntity.getBody()).thenReturn("bad response");
        responseParser.setResponse(responseEntity);
        responseParser.parseResource();
    }

    public String getJSONString(final String fileName) throws IOException {
        String path = ResponseParserTest.class.getResource(fileName).getPath();
        return IOUtils.toString(new FileInputStream(new File(path)), StandardCharsets.UTF_8.name());
    }
}
