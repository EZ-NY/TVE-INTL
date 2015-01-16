package vmn.tve.providers.nos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import vmn.tve.dto.AccessTokenDTO;
import vmn.tve.dto.CheckPermissionsRespDTO;
import vmn.tve.dto.ValidateTokenRespDTO;
import vmn.tve.providers.ResponseParser;
import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.errors.ResponseCode;
import vmn.tve.services.errors.ResponseGroup;
import vmn.tve.services.request.IncomingRequest;
import vmn.tve.services.request.IncomingRequestService;
import vmn.tve.utils.Constants;
import vmn.tve.utils.JsonUtils;

/**
 * ResponseParserImpl object intended to parse response that are returned from
 * provider.
 */
@Service
public class ResponseParserImpl implements ResponseParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseParserImpl.class);
    @Autowired
    private ApplicationContext context;

    @Autowired
    private IncomingRequestService irs;

    private ResponseEntity<String> response;

    @Override
    public String parseAccessToken() {
        return convertTokenResponse(false);
    }

    @Override
    public String parseValidateToken() {
        IncomingRequest rd = irs.getIncomingRequest();
        ValidateTokenRespDTO token = null;
        AccessTokenInfo tokenResp = JsonUtils.convertToBean(getResponse().getBody(), AccessTokenInfo.class);
        if (tokenResp != null) {
            token = new ValidateTokenRespDTO();
            token.setStatus(Constants.STATUS_SUCCESS);
            token.setFunction(Constants.API_METHOD_VALIDATE_TOKEN);
            token.setUserId(tokenResp.getUserId());
            token.setUserSA(tokenResp.getUserSA());
            token.setUserDisplayName(tokenResp.getUserDisplayName());
            token.setUserSessionId(tokenResp.getUserSessionId());
            token.setUserType(tokenResp.getUserType());
        } else {
            LOGGER.error(Constants.MSG_PROVIDER_ID + rd.getMvpdId() + Constants.DOT
                    + Constants.MSG_BAD_PROVIDER_RESPOSNE + response);
            throw new LocalServiceException(ResponseGroup.PROVIDER_BAD_RESPONSE_ERROR,
                    ResponseCode.UNRECOGNIZED_PROVIDER_RESPONSE, HttpStatus.NOT_FOUND);
        }
        return JsonUtils.convertToString(token);
    }

    @Override
    public String parseRefreshToken() {
        return convertTokenResponse(true);
    }

    @Override
    public String parseResource() {
        IncomingRequest rd = irs.getIncomingRequest();
        CheckPermissionsRespDTO permissions = null;
        UserSubscribedChannel user = JsonUtils.convertToBean(getResponse().getBody(), UserSubscribedChannel.class);
        if (user != null) {
            if (user.getChannelId() == null && user.getOdataError() == null) {
                throw new LocalServiceException(getResponse().getBody());
            }
            int authZStatus = 1;
            if (user.getOdataError() != null) {
                Message message = user.getOdataError().getMessage();
                if (message != null && message.getValue().contains(Constants.RESOURCE_NOT_FOUND)) {
                    authZStatus = 0;
                } else {
                    throw new LocalServiceException(getResponse().getBody());
                }
            }
            permissions = new CheckPermissionsRespDTO();
            permissions.setStatus(Constants.STATUS_SUCCESS);
            permissions.setFunction(Constants.API_METHOD_CHECK_PERMISSIONS);
            permissions.setState(rd.getState());
            permissions.setMvpdId(rd.getMvpdId());
            permissions.setResourceId(rd.getResourceId());
            permissions.setAuthZStatus(authZStatus);

        } else {
            throw new LocalServiceException(getResponse().getBody());
        }
        return JsonUtils.convertToString(permissions);
    }

    private String convertTokenResponse(final boolean isRefreshMethod) {
        IncomingRequest rd = irs.getIncomingRequest();
        AccessTokenDTO token = new AccessTokenDTO();
        AccessTokenInfo tokenResp = JsonUtils.convertToBean(getResponse().getBody(), AccessTokenInfo.class);
        if (tokenResp != null) {
            token.setStatus(Constants.STATUS_SUCCESS);
            if (isRefreshMethod) {
                token.setFunction(Constants.API_METHOD_REFRESH_TOKEN);
            } else {
                token.setFunction(Constants.API_METHOD_GET_ACCESS_TOKEN);
            }
            token.setMvpdId(rd.getMvpdId());
            token.setAccessToken(tokenResp.getAccessToken());
            token.setAccessTokenTTL(tokenResp.getExpiresIn());
            token.setRefreshToken(tokenResp.getRefreshToken());
            token.setRefreshTokenTTL(tokenResp.getRefreshTokenExpiresIn());
            token.setState(rd.getState());
        } else {
            LOGGER.error(Constants.MSG_PROVIDER_ID + rd.getMvpdId() + Constants.DOT
                    + Constants.MSG_BAD_PROVIDER_RESPOSNE + response);
            throw new LocalServiceException(ResponseGroup.PROVIDER_BAD_RESPONSE_ERROR,
                    ResponseCode.UNRECOGNIZED_PROVIDER_RESPONSE, HttpStatus.NOT_FOUND);
        }
        return JsonUtils.convertToString(token);
    }

    @Override
    public void setResponse(final ResponseEntity<String> response) {
        this.response = response;
    }

    public ResponseEntity<String> getResponse() {
        return response;
    }
}
