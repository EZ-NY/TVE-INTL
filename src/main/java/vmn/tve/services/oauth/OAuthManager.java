package vmn.tve.services.oauth;

/**
 * OAuthManager - interface that should be implemented to work with provider.
 */
public interface OAuthManager {
    /**
     * login step flow.
     */
    void login();

    /**
     * logout step flow.
     */
    void logout();

    /**
     * @return access token information
     */
    String getAccessTokenByAuthCode();

    /**
     * @return validate token information
     */
    String validateToken();

    /**
     * @return refresh token information
     */
    String refreshToken();

    /**
     * @return check permissions information
     */
    String checkPermissions();
}
