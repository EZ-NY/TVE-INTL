package vmn.tve.config.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.google.common.cache.CacheBuilder;

/**
 * Cache configuration.
 *
 */
@Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer {
    @Autowired
    private Environment environment;

    @Bean
    @Override
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

            @Override
            protected Cache createConcurrentMapCache(final String name) {
                CacheProperty cfg = getCacheConfig(name);
                CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();

                if (cfg.getTimeToLive() != null && cfg.getMaximumSize() != null) {
                    builder.expireAfterWrite(cfg.getTimeToLive(), TimeUnit.MINUTES);
                    builder.maximumSize(cfg.getMaximumSize());
                }

                return new ConcurrentMapCache(name, builder.build().asMap(), false);
            }
        };

        return cacheManager;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    private CacheProperty getCacheConfig(final String name) {
        CacheProperty config = new CacheProperty();
        config.setTimeToLive(getCacheProperty(name, "timeToLive", Integer.class));
        config.setMaximumSize(getCacheProperty(name, "maximumSize", Integer.class));
        return config;
    }

    private <T> T getCacheProperty(final String cacheName, final String propertyName, final Class<T> clazz) {
        String prefix = "cache." + propertyName + ".";
        T value = environment.getProperty(prefix + cacheName, clazz);

        if (value == null) {
            value = environment.getProperty(prefix + "default", clazz);
        }
        return value;
    }
}
