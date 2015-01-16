package vmn.tve.services.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.errors.ResponseCode;
import vmn.tve.services.errors.ResponseGroup;
import vmn.tve.services.oauth.request.builders.RequestBuilder;
import vmn.tve.utils.Constants;

/**
 * OAuthClient intended to send requests and receive responses from provider.
 */
@Service
public class OAuthClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthClient.class);

    @Autowired
    private RestTemplate restTemplate;

    private RequestBuilder requestBuilder;

    /**
     * @return response from appropriate provider
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ResponseEntity<String> exchange() {
        try {
            HttpEntity request = new HttpEntity(requestBuilder.buildBody(), requestBuilder.buildHeaders());
            ResponseEntity<String> response = restTemplate.exchange(requestBuilder.buildUrl(),
                    requestBuilder.buildMethod(), request, String.class);
            LOGGER.info(Constants.MSG_SUCCESSFUL_RESPONSE + response.getBody());
            return response;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.error(Constants.MSG_RESPONSE_ERROR + e.getResponseBodyAsString());
            throw new LocalServiceException(e.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
            LOGGER.error(Constants.MSG_PROVIDER_ACCESS_ERROR + e.getMessage());
            throw new LocalServiceException(ResponseGroup.PROVIDER_BAD_RESPONSE_ERROR,
                    ResponseCode.PROVIDER_ACCESS_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public void setRequestBuilder(final RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }
}
