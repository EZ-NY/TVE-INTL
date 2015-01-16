package vmn.tve.services.errors;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vmn.tve.dto.errors.ErrorResponseDTO;
import vmn.tve.services.request.IncomingRequestService;
import vmn.tve.utils.Constants;
import vmn.tve.utils.JsonUtils;

/**
 * ErrorHandlerService intended to handle all errors received from providers.
 * ErrorHandlerService fetches errormapping.file, search errors mapping for
 * appropriate provider.
 */
@Service
public class ErrorHandlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerService.class);
    private static final String LOGIN_ERROR_TEMPLATE = "?error={group}&error_description={code}";
    private static final String GROUP = "{group}";
    private static final String CODE = "{code}";

    @Value("${default.redirect_uri}")
    private String defaultRedirectUri;

    @Autowired
    private ErrorMappingService errorCodeMapping;

    @Autowired
    private IncomingRequestService irs;

    /**
     * @param e - an error that has been thrown from specific place of local
     *            service.
     * @param response - http response.
     * @return an error in json format conformed to be returned to centralized
     *         service.
     */
    public String handle(final LocalServiceException e, final HttpServletResponse response) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setStatus(Constants.STATUS_ERROR);

        if (e.getResponseGroup() != null) {
            error.setGroup(e.getResponseGroup().getName());
            error.setErrorCode(e.getResponseCode().getName());
            response.setStatus(e.getStatusCode().value());
        } else if (e.getMessage() != null) {
            String providerErrorCode = parseErrorCode(e.getMessage());
            List<ErrorCode> errorCodes = errorCodeMapping.getErrorCodes(irs.getIncomingRequest().getMvpdId());
            if (errorCodes != null && providerErrorCode != null) {
                Iterator<ErrorCode> it = errorCodes.iterator();
                while (it.hasNext()) {
                    ErrorCode errorCode = it.next();
                    if (errorCode.getCode().equalsIgnoreCase(providerErrorCode)
                            || providerErrorCode.startsWith(errorCode.getCode())) {
                        error.setGroup(errorCode.getGroup().trim());
                        error.setErrorCode(errorCode.getCSErrorCode().trim());
                        response.setStatus(errorCode.getStatusCode());
                        break;
                    }
                }
            }
        }
        if (error.getGroup().isEmpty()) {
            error.setGroup(ResponseGroup.PROVIDER_BAD_RESPONSE_ERROR.getName());
            error.setErrorCode(ResponseCode.UNRECOGNIZED_PROVIDER_RESPONSE.getName());
            response.setStatus(HttpStatus.NOT_FOUND.value());
            e.printStackTrace();
        }
        return JsonUtils.convertToString(error);
    }

    /**
     * @param e - an error that has been thrown from specific place of local
     *            service only during login flow.
     * @param response - http response.
     */
    public void handleLoginException(final LocalServiceException e, final HttpServletResponse response) {
        try {
            String url = null;

            if (e.getResponseGroup().equals(ResponseGroup.WHITELIST_ERROR)) {
                if (e.getResponseCode().equals(ResponseCode.WHITELIST_INVALID_PROVIDER)) {
                    url = getRedirectUri()
                            + LOGIN_ERROR_TEMPLATE.replace(GROUP, ResponseGroup.LOGIN_INVALID_REQUEST.getName())
                                    .replace(CODE, e.getResponseCode().getName());
                } else if (e.getResponseCode().equals(ResponseCode.WHITELIST_PARSE_ERROR)) {
                    url = getRedirectUri()
                            + LOGIN_ERROR_TEMPLATE.replace(GROUP, ResponseGroup.SERVER_ERROR.getName()).replace(CODE,
                                    e.getResponseCode().getName());
                }
            } else {
                url = getRedirectUri()
                        + LOGIN_ERROR_TEMPLATE.replace(GROUP, e.getResponseGroup().getName()).replace(CODE,
                                e.getResponseCode().getName());
            }
            response.sendRedirect(url);
        } catch (IOException e1) {
            LOGGER.error(Constants.MSG_INTERNAL_SERVER_ERROR + e.getMessage());
        }
    }

    private String parseErrorCode(final String errorMessage) {
        String result = null;
        Error error = JsonUtils.convertToBean(errorMessage, Error.class);
        if (error != null) {
            if (error.getError() != null) {
                result = error.getError();
            } else if (error.getFault() != null) {
                result = error.getFault().getFaultstring();
            }
        }
        return result;
    }

    /**
     * @param e - any unexpected and unknown errors that can occur in local
     *            service
     * @param response - http response.
     * @return an error in json format conformed to be returned to centralized
     *         service.
     */
    public String createDefaultError(final Exception e, final HttpServletResponse response) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setStatus(Constants.STATUS_ERROR);
        error.setGroup(ResponseGroup.INTERNAL_SERVER_ERROR.getName());
        error.setErrorCode(ResponseCode.UNRECOGNIZED_ERROR.getName());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        e.printStackTrace();
        return JsonUtils.convertToString(error);
    }

    /**
     * any unexpected and unknown errors that can occur in local service only
     * during login flow.
     *
     * @param response - http response.
     */
    public void createDefaultLoginError(final HttpServletResponse response) {
        try {
            response.sendRedirect(getRedirectUri()
                    + LOGIN_ERROR_TEMPLATE.replace(GROUP, ResponseGroup.SERVER_ERROR.getName()).replace(CODE,
                            ResponseCode.UNRECOGNIZED_ERROR.getName()));
        } catch (IOException e) {
            LOGGER.error(Constants.MSG_INTERNAL_SERVER_ERROR + e.getMessage());
        }
    }

    private String getRedirectUri() {
        String redirectUri = irs.getIncomingRequest().getRedirectUri();
        if (redirectUri == null || redirectUri.isEmpty()) {
            redirectUri = defaultRedirectUri;
        }
        return redirectUri;
    }
}
