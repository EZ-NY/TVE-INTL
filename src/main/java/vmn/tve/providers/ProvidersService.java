package vmn.tve.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import vmn.tve.services.oauth.OAuthManager;
import vmn.tve.services.whitelist.client.Provider;
import vmn.tve.utils.Constants;

/**
 * ProvidersService class intended to delegate any methods call to appropriate
 * provider.
 */
@Service
public class ProvidersService {
    @Autowired
    private ApplicationContext context;

    public String getProviders() {
        return getProvidersMerger().getProviders();
    }

    /**
     * call login flow of appropriate service manager.
     */
    public void login() {
        getServiceManager().login();
    }

    /**
     * call logout flow of appropriate service manager.
     */
    public void logout() {
        getServiceManager().logout();
    }

    /**
     * call get access token method passing authorization code.
     * @return access token and refresh token information in json format.
     */
    public String getAccessTokenByAuthCode() {
        return getServiceManager().getAccessTokenByAuthCode();
    }

    /**
     * call validate access token to check if token is not expired.
     * @return validation information in json format.
     */
    public String validateToken() {
        return getServiceManager().validateToken();
    }

    /**
     * call refresh token to get new access token and new refresh token.
     * @return token information in json format.
     */
    public String refreshToken() {
        return getServiceManager().refreshToken();
    }

    /**
     * call check permission to verify if user has permission for specific
     * resource.
     * @return token information in json format.
     */
    public String checkPermissions() {
        return getServiceManager().checkPermissions();
    }

    private OAuthManager getServiceManager() {
        Provider provider = getProvidersMerger().validateProvider();
        return (OAuthManager) context.getBean(provider.getServiceManager());
    }

    private ProvidersMerger getProvidersMerger() {
        return (ProvidersMerger) context.getBean(Constants.PROVIDERS_MERGER);
    }
}
