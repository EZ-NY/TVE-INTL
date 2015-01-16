package vmn.tve.config;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import vmn.tve.config.cache.CacheConfiguration;
import vmn.tve.providers.DataProvider;
import vmn.tve.providers.ProvidersMerger;
import vmn.tve.providers.ResponseParser;
import vmn.tve.providers.nos.DataProviderImpl;
import vmn.tve.providers.nos.ResponseParserImpl;
import vmn.tve.services.errors.ErrorHandlerService;
import vmn.tve.services.isis.ISISService;
import vmn.tve.services.oauth.CommonOAuthManager;
import vmn.tve.services.oauth.OAuthClient;
import vmn.tve.services.oauth.request.builders.RequestBuilderAccessResource;
import vmn.tve.services.oauth.request.builders.RequestBuilderAccessToken;
import vmn.tve.services.oauth.request.builders.RequestBuilderLogin;
import vmn.tve.services.oauth.request.builders.RequestBuilderLogout;
import vmn.tve.services.oauth.request.builders.RequestBuilderRefreshToken;
import vmn.tve.services.oauth.request.builders.RequestBuilderValidateToken;
import vmn.tve.services.whitelist.WhiteListService;
import vmn.tve.services.whitelist.parser.WhiteListParser;

/**
 * Core configuration class intended as main point to describe beans and
 * services that can be accessed in app.
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = "vmn.tve", excludeFilters = { @Filter(type = FilterType.REGEX,
        pattern = { "vmn.tve.config.CoreConfiguration" }) })
@PropertySources(value = { @PropertySource("classpath:common.properties"),
        @PropertySource("classpath:site-${stage}.properties") })
@Import({ CacheConfiguration.class })
public class CoreConfiguration extends WebMvcConfigurerAdapter {

    /**
     * @return placeholder configurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pc = new PropertySourcesPlaceholderConfigurer();
        pc.setIgnoreResourceNotFound(true);
        return pc;
    }

    /**
     * @return locale resolver
     */
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }

    /**
     * @return rest template
     */
    @Bean(name = "restTemplate")
    @Scope(value = "prototype")
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }

    /**
     * @return protocols
     */
    @Bean(name = "protocols")
    public HashMap<String, String> protocols() {
        HashMap<String, String> protocols = new HashMap<String, String>();
        protocols.put("CommonOAuthManager", "oauth2.0");
        return protocols;
    }

    @Bean(name = "RequestBuilderLogin")
    @Scope(value = "request")
    public RequestBuilderLogin getLoginRequestBuilder() {
        return new RequestBuilderLogin();
    }

    @Bean(name = "RequestBuilderLogout")
    @Scope(value = "request")
    public RequestBuilderLogout getLogoutRequestBuilder() {
        return new RequestBuilderLogout();
    }

    @Bean(name = "RequestBuilderAccessToken")
    @Scope(value = "request")
    public RequestBuilderAccessToken getAccessTokenRequestBuilder() {
        return new RequestBuilderAccessToken();
    }

    @Bean(name = "RequestBuilderValidateToken")
    @Scope(value = "request")
    public RequestBuilderValidateToken getValidateTokenBuilder() {
        return new RequestBuilderValidateToken();
    }

    @Bean(name = "RequestBuilderRefreshToken")
    @Scope(value = "request")
    public RequestBuilderRefreshToken getRefreshTokenBuilder() {
        return new RequestBuilderRefreshToken();
    }

    @Bean(name = "RequestBuilderAccessResource")
    @Scope(value = "request")
    public RequestBuilderAccessResource getAccessResourceBuilder() {
        return new RequestBuilderAccessResource();
    }

    @Bean(name = "ResponseParserImpl")
    @Scope(value = "request")
    public ResponseParser getDefaultResponseParserImpl() {
        return new ResponseParserImpl();
    }

    @Bean(name = "DataProviderImpl")
    @Scope(value = "request")
    public DataProvider getDefaultDataProviderImpl() {
        return new DataProviderImpl();
    }

    @Bean(name = "CommonOAuthManager")
    @Scope(value = "request")
    public CommonOAuthManager getCommonOAuthManager() {
        return new CommonOAuthManager();
    }

    @Bean(name = "OauthClient")
    @Scope(value = "request")
    public OAuthClient getOAuthClient() {
        return new OAuthClient();
    }

    @Bean(name = "ProvidersMerger")
    @Scope(value = "request")
    public ProvidersMerger getProvidersMerger() {
        return new ProvidersMerger();
    }

    @Bean(name = "WhiteListParser")
    @Scope(value = "request")
    public WhiteListParser getWhiteListParser() {
        return new WhiteListParser();
    }

    @Bean(name = "WhiteListService")
    @Scope(value = "request")
    public WhiteListService getWhiteListService() {
        return new WhiteListService();
    }

    @Bean(name = "IsisService")
    @Scope(value = "request")
    public ISISService getIsisService() {
        return new ISISService();
    }

    @Bean(name = "ErrorHandlerService")
    @Scope(value = "request")
    public ErrorHandlerService getErrorHandlerService() {
        return new ErrorHandlerService();
    }
}
