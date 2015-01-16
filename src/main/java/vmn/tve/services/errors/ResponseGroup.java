package vmn.tve.services.errors;

/**
 * all response groups that can be sent to centralized service.
 */
public enum ResponseGroup {
    WHITELIST_ERROR("whitelist_error"),
    ISIS_ERROR("isis_error"),
    PROVIDER_BAD_REQUEST_ERROR("provider_bad_request_error"),
    PROVIDER_BAD_RESPONSE_ERROR("provider_bad_response_error"),
    AUTHORIZATION_ERROR("authorization_error"),
    LOCAL_SERVICE_CONFIGURATION_ERROR("local_service_configuration_error"),
    INTERNAL_SERVER_ERROR("internal_server_error"),

    // login flow response groups
    LOGIN_INVALID_REQUEST("invalid_request"),
    LOGIN_INVALID_SCOPE("invalid_scope"),
    SERVER_ERROR("server_error");

    private String name;

    private ResponseGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name.toLowerCase();
    }
}
