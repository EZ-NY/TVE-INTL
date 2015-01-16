package vmn.tve.providers;

import vmn.tve.services.oauth.request.builders.OAuthRequest;

/**
 * DataProvider interface has to be implemented to gather configuration
 * information that will be passed to provider.
 */
public interface DataProvider {
    /**
     * @return request with data for login step
     */
    OAuthRequest loadLoginData();

    /**
     * @return request with data for logout step
     */
    OAuthRequest loadLogoutData();

    /**
     * @return request with data for access token step
     */
    OAuthRequest loadAccessTokenData();

    /**
     * @return request with data validate token step
     */
    OAuthRequest loadValidateTokenData();

    /**
     * @return request with data for refresh token step
     */
    OAuthRequest loadRefreshTokenData();

    /**
     * @return request with data for check permission step
     */
    OAuthRequest loadCheckPermissionsData();
}
