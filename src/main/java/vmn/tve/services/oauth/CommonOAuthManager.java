package vmn.tve.services.oauth;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import vmn.tve.providers.DataProvider;
import vmn.tve.providers.ResponseParser;
import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.errors.ResponseCode;
import vmn.tve.services.errors.ResponseGroup;
import vmn.tve.services.oauth.request.builders.RequestBuilder;
import vmn.tve.services.request.IncomingRequest;
import vmn.tve.services.request.IncomingRequestService;
import vmn.tve.utils.Constants;

/**
 * CommonOAuthManager is intended to handle requests and responses that
 * send/received from provider.
 */
@Service
public class CommonOAuthManager implements OAuthManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonOAuthManager.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IncomingRequestService irs;

    @Override
    public void login() {
        IncomingRequest rd = irs.getIncomingRequest();
        String url = null;
        try {
            RequestBuilder builder = getRequestBuilder(rd.getMvpdId(), Constants.DEFAULT_LOGIN_REQ_BUILDER);
            builder.setRequest(getDataProvider(rd.getMvpdId()).loadLoginData());
            url = builder.buildUrl();
            LOGGER.debug(Constants.SEND_LOGIN_REDIRECT + url);
            rd.getResponse().sendRedirect(url);
        } catch (IOException e) {
            LOGGER.error(Constants.MSG_SEND_REDIRECT + url);
            throw new LocalServiceException(ResponseGroup.SERVER_ERROR, ResponseCode.UNRECOGNIZED_ERROR);
        }
    }

    @Override
    public void logout() {
        IncomingRequest rd = irs.getIncomingRequest();
        String url = null;
        try {
            RequestBuilder builder = getRequestBuilder(rd.getMvpdId(), Constants.DEFAULT_LOGOUT_REQ_BUILDER);
            builder.setRequest(getDataProvider(rd.getMvpdId()).loadLogoutData());
            url = builder.buildUrl();
            LOGGER.debug(Constants.SEND_LOGOUT_REDIRECT + url);
            rd.getResponse().sendRedirect(url);
        } catch (IOException e) {
            LOGGER.error(Constants.MSG_SEND_REDIRECT + url);
            throw new LocalServiceException(ResponseGroup.SERVER_ERROR, ResponseCode.UNRECOGNIZED_ERROR);
        }
    }

    @Override
    public String getAccessTokenByAuthCode() {
        IncomingRequest rd = irs.getIncomingRequest();
        RequestBuilder builder = getRequestBuilder(rd.getMvpdId(), Constants.DEFAULT_ACCESS_TOKEN_REQ_BUILDER);
        ResponseParser parser = getResponseParser(rd.getMvpdId());
        builder.setRequest(getDataProvider(rd.getMvpdId()).loadAccessTokenData());
        getOAuthClient().setRequestBuilder(builder);
        parser.setResponse(getOAuthClient().exchange());
        return parser.parseAccessToken();
    }

    @Override
    public String validateToken() {
        IncomingRequest rd = irs.getIncomingRequest();
        RequestBuilder builder = getRequestBuilder(rd.getMvpdId(), Constants.DEFAULT_VALIDATE_TOKEN_REQ_BILDER);
        ResponseParser parser = getResponseParser(rd.getMvpdId());
        builder.setRequest(getDataProvider(rd.getMvpdId()).loadValidateTokenData());
        getOAuthClient().setRequestBuilder(builder);
        parser.setResponse(getOAuthClient().exchange());
        return parser.parseValidateToken();
    }

    @Override
    public String refreshToken() {
        IncomingRequest rd = irs.getIncomingRequest();
        RequestBuilder builder = getRequestBuilder(rd.getMvpdId(), Constants.DEFAULT_REFRESH_TOKEN_REQ_BUILDER);
        ResponseParser parser = getResponseParser(rd.getMvpdId());
        builder.setRequest(getDataProvider(rd.getMvpdId()).loadRefreshTokenData());
        getOAuthClient().setRequestBuilder(builder);
        parser.setResponse(getOAuthClient().exchange());
        return parser.parseRefreshToken();
    }

    @Override
    public String checkPermissions() {
        IncomingRequest rd = irs.getIncomingRequest();
        RequestBuilder builder = getRequestBuilder(rd.getMvpdId(), Constants.DEFAULT_ACCESS_RESOURCE_REQ_BUILDER);
        ResponseParser parser = getResponseParser(rd.getMvpdId());
        builder.setRequest(getDataProvider(rd.getMvpdId()).loadCheckPermissionsData());
        getOAuthClient().setRequestBuilder(builder);
        ResponseEntity<String> response = null;
        try {
            response = getOAuthClient().exchange();
            parser.setResponse(response);
        } catch (LocalServiceException e) {
            if (e.getMessage() != null && !e.getMessage().isEmpty()) {
                parser.setResponse(new ResponseEntity<String>(e.getMessage(), null, null));
            } else {
                throw e;
            }
        }
        return parser.parseResource();
    }

    private RequestBuilder getRequestBuilder(final String mvpdId, final String defaultBuilderId) {
        RequestBuilder builder = null;
        try {
            builder = (RequestBuilder) context.getBean(mvpdId.toUpperCase() + defaultBuilderId);
        } catch (NoSuchBeanDefinitionException e) {
            builder = (RequestBuilder) context.getBean(defaultBuilderId);
        }
        return builder;
    }

    private ResponseParser getResponseParser(final String mvpdId) {
        ResponseParser parser = null;
        try {
            parser = (ResponseParser) context.getBean(mvpdId.toUpperCase().toUpperCase()
                    + Constants.DEFAULT_RESPONSE_PARSER);
        } catch (NoSuchBeanDefinitionException e) {
            parser = (ResponseParser) context.getBean(Constants.DEFAULT_RESPONSE_PARSER);
        }
        return parser;
    }

    private DataProvider getDataProvider(final String mvpdId) {
        DataProvider dataProvider = null;
        try {
            dataProvider = (DataProvider) context.getBean(mvpdId.toUpperCase().toUpperCase()
                    + Constants.DEFAULT_DATA_PROVIDER);
        } catch (NoSuchBeanDefinitionException e) {
            dataProvider = (DataProvider) context.getBean(Constants.DEFAULT_DATA_PROVIDER);
        }
        return dataProvider;
    }

    private OAuthClient getOAuthClient() {
        return (OAuthClient) context.getBean(Constants.OAUTH_CLIENT);
    }
}
