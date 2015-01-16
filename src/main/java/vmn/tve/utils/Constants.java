package vmn.tve.utils;

/**
 * Constants class to retain all static messages.
 */
public final class Constants {

    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String NOT_IMPLEMENTED = "Not implemented";
    public static final String OAUTH_ENDPOINT_URL = "oauth.endpoint";
    public static final String RESOURCES_ENDPOINT_URL = "resources.endpoint";
    public static final String METHOD_LOGIN = "method.login";
    public static final String METHOD_LOGOUT = "method.logout";
    public static final String METHOD_GET_ACCESS_TOKEN = "method.accesstoken";
    public static final String METHOD_VALIDATE_TOKEN = "method.tokeninfo";
    public static final String METHOD_REFRESH_TOKEN = "method.refreshtoken";
    public static final String METHOD_CHECK_PERMISSIONS = "method.checkpermissions";
    public static final String PROVIDER_CHANNEL_ID = "channel.id";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";
    public static final String API_GET_PROVIDERS_LIST = "getProvidersList";
    public static final String API_METHOD_GET_ACCESS_TOKEN = "getAccessToken";
    public static final String API_METHOD_VALIDATE_TOKEN = "validateToken";
    public static final String API_METHOD_REFRESH_TOKEN = "refreshToken";
    public static final String API_METHOD_CHECK_PERMISSIONS = "checkPermissions";
    public static final String FORMAT = "$format";
    public static final String FORMAT_JSON = "json";
    public static final String FORCE_LOGOUT = "force_logout";
    public static final String PROVIDER_TYPE_EXCEPTION = "PROVIDER_ERROR";
    public static final String WHITELIST_TYPE_EXCEPTION = "WHITELIST_ERROR";
    public static final String ISIS_TYPE_EXCEPTION = "ISIS_ERROR";
    public static final String MSG_BAD_PROVIDER_RESPOSNE = "Impossible to handle response received from provider: ";
    public static final String MSG_PROVIDER_ID = "Provider Id: ";
    public static final String SEND_LOGIN_REDIRECT = "Send login redirect to url: ";
    public static final String SEND_LOGOUT_REDIRECT = "Send logout redirect to url: ";
    public static final String MSG_SEND_REDIRECT = "Impossible to send redirect from local service to provider. Url: ";
    public static final String MSG_RESPONSE_ERROR = "Response has been received from provider with an error message: ";
    public static final String MSG_PROVIDER_ACCESS_ERROR = "Can not connect to provider: ";
    public static final String MSG_SUCCESSFUL_RESPONSE = "Successful response has been received from provider: ";
    public static final String MSG_INVALID_PROVIDER_ID = "Impossible to find provider in whitelist with id: ";
    public static final String MSG_ERRORMAPPING = "Impossible to handle errormapping file. Mapping file path: ";
    public static final String MSG_JSON_PARSE = "Impossible to parse the following json response: ";
    public static final String MSG_JSON_CREATE = "Impossible to create json object";
    public static final String MSG_INTERNAL_SERVER_ERROR = "Unhandled Internal Server Error: ";
    public static final String MSG_PICKER_LOGO_UNDEFINED = "Picker logo is undefined for MVPD id: ";
    public static final String MSG_ISIS_CONNECTION_ERROR = "Stream failure. Connection error, URL: ";
    public static final String MSG_WHITE_LIST_PARSE_ERROR = "Can not find or parse the following resource: ";
    public static final String MSG_ENDPOINT_URL_EMPTY = "Endpoint url is empty. Check configuration file.";
    public static final String MSG_METHOD_URL_EMPTY = "Part of url (method) is empty. Check configuration file.";
    public static final String MSG_CLIENT_ID_EMPTY = "Client id is empty. Check configuration file.";
    public static final String MSG_CLIENT_SECRET_EMPTY = "Client secret is empty. Check configuration file.";
    public static final String MSG_SCOPE_EMPTY = "Scope is empty. Check configuration file.";
    public static final String MSG_STATE_EMPTY = "State is empty. Check incoming request parameters.";
    public static final String MSG_REDIRECT_URI_EMPTY = "Redirect uri is empty. Check incoming request parameters.";
    public static final String MSG_AUTH_CODE_EMPTY = "Authorization code is empty. Check incoming request parameters.";
    public static final String MSG_ACCESS_TOKEN_EMPTY = "Access token is empty. Check incoming request parameters.";
    public static final String MSG_REFRESH_TOKEN_EMPTY = "Refresh token is empty. Check incoming request parameters.";
    public static final String MSG_INCORRECT_LOCALE = "Locale is empty or does not match pattern langCode_countryCode";
    public static final String MSG_DEVICE_TYPE_EMPTY = "Device type is empty. Check incoming request parameters.";
    public static final String MSG_CHANNEL_ID_EMPTY = "Channel id is empty. Check configuration file.";
    public static final String MSG_ISIS_DATA_MISSED = "Did not found ISIS data for following providers: ";
    public static final String MSG_ISIS_RESPONSE = "Starting to parse response from ISIS: ";
    public static final String MSG_OPEN_CONNECTION = "Opening connection to ";
    public static final String MSG_PARSED_ISIS_RESPONSE = "Parsed isis response result: ";
    public static final String RESOURCE_NOT_FOUND = "Resource not found for the segment 'SubscribedChannels'";
    public static final String DEFAULT_LOGIN_REQ_BUILDER = "RequestBuilderLogin";
    public static final String DEFAULT_LOGOUT_REQ_BUILDER = "RequestBuilderLogout";
    public static final String DEFAULT_ACCESS_TOKEN_REQ_BUILDER = "RequestBuilderAccessToken";
    public static final String DEFAULT_VALIDATE_TOKEN_REQ_BILDER = "RequestBuilderValidateToken";
    public static final String DEFAULT_REFRESH_TOKEN_REQ_BUILDER = "RequestBuilderRefreshToken";
    public static final String DEFAULT_ACCESS_RESOURCE_REQ_BUILDER = "RequestBuilderAccessResource";
    public static final String DEFAULT_DATA_PROVIDER = "DataProviderImpl";
    public static final String DEFAULT_RESPONSE_PARSER = "ResponseParserImpl";
    public static final String DEFAULT_REQUEST_VALIDATOR = "RequestValidatorImpl";
    public static final String WHITE_LIST_SERVICE = "WhiteListService";
    public static final String WHITE_LIST_PARSER = "WhiteListParser";
    public static final String ISIS_SERVICE = "IsisService";
    public static final String PROVIDERS_MERGER = "ProvidersMerger";
    public static final String ERROR_HANDLER = "ErrorHandlerService";
    public static final String OAUTH_CLIENT = "OauthClient";

    private Constants() {
    }
}
