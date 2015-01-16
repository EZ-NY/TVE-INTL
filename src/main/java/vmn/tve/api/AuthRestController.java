package vmn.tve.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vmn.tve.providers.ProvidersService;
import vmn.tve.services.errors.ErrorHandlerService;
import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.request.IncomingRequestService;
import vmn.tve.utils.Constants;

/**
 * Entry API point.
 */
@Controller
@RequestMapping("/tveauth/v1")
public class AuthRestController {

    @Autowired
    private ProvidersService providersService;

    @Autowired
    private IncomingRequestService irs;

    @Autowired
    private ApplicationContext context;

    /**
     * @param componentId - componentId
     * @param requestorId - requestorId
     * @param locale - locale
     * @param deviceType - deviceType
     * @param request - request
     * @param response - response
     * @return list of providers in json format
     */
    @RequestMapping(value = "{componentId}/{requestorId}/getProvidersList", method = RequestMethod.GET, params = {
            "locale", "deviceType" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String getProvidersList(@PathVariable("componentId") final String componentId,
            @PathVariable("requestorId") final String requestorId, @RequestParam(value = "locale") final String locale,
            @RequestParam(value = "deviceType") final String deviceType, final HttpServletRequest request,
            final HttpServletResponse response) {
        try {
            irs.setIncomingRequest(false, componentId, requestorId, locale, deviceType, null, null, null, null, null, null,
                    null, request, response);
            return providersService.getProviders();
        } catch (LocalServiceException e) {
            return getErrorHandlerService().handle(e, response);
        } catch (Exception e) {
            return getErrorHandlerService().createDefaultError(e, response);
        }
    }

    /**
     * @param componentId - componentId
     * @param requestorId - requestorId
     * @param locale - locale
     * @param deviceType - deviceType
     * @param state - state
     * @param mvpdId - mvpdId
     * @param redirectUrl - redirectUrl
     * @param request - request
     * @param response - response
     */
    @RequestMapping(value = "{componentId}/{requestorId}/getLogin", method = RequestMethod.GET, params = { "locale",
            "deviceType", "state", "mvpdId", "redirectUrl" })
    public void getLogin(@PathVariable("componentId") final String componentId,
            @PathVariable("requestorId") final String requestorId, @RequestParam(value = "locale") final String locale,
            @RequestParam(value = "deviceType") final String deviceType,
            @RequestParam(value = "state") final String state, @RequestParam(value = "mvpdId") final String mvpdId,
            @RequestParam(value = "redirectUrl") final String redirectUrl, final HttpServletRequest request,
            final HttpServletResponse response) {
        try {
            irs.setIncomingRequest(true, componentId, requestorId, locale, deviceType, state, mvpdId, redirectUrl, null, null,
                    null, null, request, response);
            providersService.login();
        } catch (LocalServiceException e) {
            getErrorHandlerService().handleLoginException(e, response);
        } catch (Exception e) {
            getErrorHandlerService().createDefaultLoginError(response);
        }
    }

    /**
     * @param componentId - componentId
     * @param requestorId - requestorId
     * @param locale - locale
     * @param deviceType - deviceType
     * @param mvpdId - mvpdId
     * @param redirectUrl - redirectUrl
     * @param accessToken - accessToken
     * @param request - request
     * @param response - response
     */
    @RequestMapping(value = "{componentId}/{requestorId}/logout", method = RequestMethod.GET, params = { "locale",
            "deviceType", "mvpdId", "redirectUrl", "accessToken" })
    public void logout(@PathVariable("componentId") final String componentId,
            @PathVariable("requestorId") final String requestorId, @RequestParam(value = "locale") final String locale,
            @RequestParam(value = "deviceType") final String deviceType,
            @RequestParam(value = "mvpdId") final String mvpdId,
            @RequestParam(value = "redirectUrl") final String redirectUrl,
            @RequestParam(value = "accessToken") final String accessToken, final HttpServletRequest request,
            final HttpServletResponse response) {
        try {
            irs.setIncomingRequest(true, componentId, requestorId, locale, deviceType, null, mvpdId, redirectUrl, accessToken,
                    null, null, null, request, response);
            providersService.logout();
        } catch (LocalServiceException e) {
            getErrorHandlerService().handleLoginException(e, response);
        } catch (Exception e) {
            getErrorHandlerService().createDefaultLoginError(response);
        }
    }

    /**
     * @param componentId - componentId
     * @param requestorId - requestorId
     * @param locale - locale
     * @param deviceType - deviceType
     * @param authorizationCode - authorizationCode
     * @param mvpdId - mvpdId
     * @param state - state
     * @param redirectUrl - redirectUrl
     * @param request - request
     * @param response - response
     * @return access token and refresh token information in json format
     */
    @RequestMapping(value = "{componentId}/{requestorId}/getAccessToken", method = RequestMethod.POST, params = {
            "locale", "deviceType", "accessCode", "mvpdId", "state", "redirectUrl", },
            consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String getAccessToken(@PathVariable("componentId") final String componentId,
            @PathVariable("requestorId") final String requestorId, @RequestParam(value = "locale") final String locale,
            @RequestParam(value = "deviceType") final String deviceType,
            @RequestParam(value = "accessCode") final String authorizationCode,
            @RequestParam(value = "mvpdId") final String mvpdId, @RequestParam(value = "state") final String state,
            @RequestParam(value = "redirectUrl") final String redirectUrl, final HttpServletRequest request,
            final HttpServletResponse response) {
        try {
            irs.setIncomingRequest(false, componentId, requestorId, locale, deviceType, state, mvpdId, redirectUrl, null,
                    authorizationCode, null, null, request, response);
            return providersService.getAccessTokenByAuthCode();
        } catch (LocalServiceException e) {
            return getErrorHandlerService().handle(e, response);
        } catch (Exception e) {
            return getErrorHandlerService().createDefaultError(e, response);
        }
    }

    /**
     * @param componentId - componentId
     * @param requestorId - requestorId
     * @param locale - locale
     * @param deviceType - deviceType
     * @param accessToken - accessToken
     * @param mvpdId - mvpdId
     * @param request - request
     * @param response - response
     * @return token validation information
     */
    @RequestMapping(value = "{componentId}/{requestorId}/validateToken", method = RequestMethod.GET, params = {
            "locale", "deviceType", "accessToken", "mvpdId" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String validateToken(@PathVariable("componentId") final String componentId,
            @PathVariable("requestorId") final String requestorId, @RequestParam(value = "locale") final String locale,
            @RequestParam(value = "deviceType") final String deviceType,
            @RequestParam(value = "accessToken") final String accessToken,
            @RequestParam(value = "mvpdId") final String mvpdId, final HttpServletRequest request,
            final HttpServletResponse response) {
        try {
            irs.setIncomingRequest(false, componentId, requestorId, locale, deviceType, null, mvpdId, null, accessToken, null,
                    null, null, request, response);
            return providersService.validateToken();
        } catch (LocalServiceException e) {
            return getErrorHandlerService().handle(e, response);
        } catch (Exception e) {
            return getErrorHandlerService().createDefaultError(e, response);
        }
    }

    /**
     * @param componentId - componentId
     * @param requestorId - requestorId
     * @param locale - locale
     * @param deviceType - deviceType
     * @param refreshToken - refreshToken
     * @param mvpdId - mvpdId
     * @param state - state
     * @param request - request
     * @param response - response
     * @return new refreshed token
     */
    @RequestMapping(value = "{componentId}/{requestorId}/refreshToken", method = RequestMethod.POST, params = {
            "locale", "deviceType", "refreshToken", "mvpdId", "state" },
            consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String refreshToken(@PathVariable("componentId") final String componentId,
            @PathVariable("requestorId") final String requestorId, @RequestParam(value = "locale") final String locale,
            @RequestParam(value = "deviceType") final String deviceType,
            @RequestParam(value = "refreshToken") final String refreshToken,
            @RequestParam(value = "mvpdId") final String mvpdId, @RequestParam(value = "state") final String state,
            final HttpServletRequest request, final HttpServletResponse response) {
        try {
            irs.setIncomingRequest(false, componentId, requestorId, locale, deviceType, state, mvpdId, null, null, null,
                    refreshToken, null, request, response);
            return providersService.refreshToken();
        } catch (LocalServiceException e) {
            return getErrorHandlerService().handle(e, response);
        } catch (Exception e) {
            return getErrorHandlerService().createDefaultError(e, response);
        }
    }

    /**
     * @param componentId - componentId
     * @param requestorId - requestorId
     * @param locale - locale
     * @param deviceType - deviceType
     * @param accessToken - accessToken
     * @param mvpdId - mvpdId
     * @param state - state
     * @param resourceId - resourceId
     * @param request - request
     * @param response - response
     * @return user permissions information
     */
    @RequestMapping(value = "{componentId}/{requestorId}/checkPermissions", method = RequestMethod.GET, params = {
            "locale", "deviceType", "accessToken", "mvpdId", "state", "resourceId" },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String checkPermissions(@PathVariable("componentId") final String componentId,
            @PathVariable("requestorId") final String requestorId, @RequestParam(value = "locale") final String locale,
            @RequestParam(value = "deviceType") final String deviceType,
            @RequestParam(value = "accessToken") final String accessToken,
            @RequestParam(value = "mvpdId") final String mvpdId, @RequestParam(value = "state") final String state,
            @RequestParam(value = "resourceId") final String resourceId, final HttpServletRequest request,
            final HttpServletResponse response) {
        try {
            irs.setIncomingRequest(false, componentId, requestorId, locale, deviceType, state, mvpdId, null, accessToken, null,
                    null, resourceId, request, response);
            return providersService.checkPermissions();
        } catch (LocalServiceException e) {
            return getErrorHandlerService().handle(e, response);
        } catch (Exception e) {
            return getErrorHandlerService().createDefaultError(e, response);
        }
    }

    /**
     * @return test health page
     */
    @RequestMapping(value = "/checkHealthPage", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String checkHealthPage() {
        return "{\"test\": \"local service response\"}";
    }

    private ErrorHandlerService getErrorHandlerService() {
        return (ErrorHandlerService) context.getBean(Constants.ERROR_HANDLER);
    }
}
