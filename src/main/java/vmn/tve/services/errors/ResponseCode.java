package vmn.tve.services.errors;

/**
 * all codes that can be sent to centralized service.
 */
public enum ResponseCode {

    ISIS_PICKER_UNSPECIFIED("isis_picker_unspecified"),
    ISIS_UNKNOWN_IMAGE_FORMAT("isis_unknown_image_format"),
    ISIS_PROVIDER_CONFIGURATION_MISSING("isis_provider_configuration_missing"),
    ISIS_BRAND_COUNTRYCODE_MAPPING_MISSING("isis_brand_countrycode_mapping_missing"),
    ISIS_CONNECTION_ERROR("isis_connection_error"),
    WHITELIST_PARSE_ERROR("whitelist_parse_error"),
    WHITELIST_INVALID_PROVIDER("whitelist_invalid_provider"),
    UNRECOGNIZED_ERROR("unrecognized_error"),
    UNRECOGNIZED_PROVIDER_RESPONSE("unrecognized_provider_response"),
    CLIENT_ID_EMPTY("client_id_empty"),
    CLIENT_SECRET_EMPTY("client_secret_empty"),
    ENDPOINT_URL_EMPTY("endpoint_url_empty"),
    ENDPOINT_METHOD_EMPTY("endpoint_method_empty"),
    ACCESS_TOKEN_EMPTY("access_token_empty"),
    AUTHORIZATION_CODE_EMPTY("authorization_code_empty"),
    REDIRECT_URI_EMPTY("redirect_uri_empty"),
    REFRESH_TOKEN_EMPTY("refresh_token_empty"),
    DEVICE_TYPE_ID_EMPTY("devicetype_empty"),
    CHANNEL_ID_EMPTY("channel_id_empty"),
    INVALID_LOCALE("invalid_locale"),
    LOCAL_SERVICE_CONFIGURATION_ERROR("local_service_configuration_error"),
    PROVIDER_ACCESS_ERROR("provider_access_error");

    private String name;

    private ResponseCode(String name) {
        this.name = name;
    }

    public String getName() {
        return name.toLowerCase();
    }
}
