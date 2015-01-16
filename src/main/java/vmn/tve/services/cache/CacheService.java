package vmn.tve.services.cache;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * CacheService.
 */
@Service
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    /**
     * Clear specific cache.
     * @param name - specific cache name
     */
    public void clearCache(final String name) {
        Cache cache = cacheManager.getCache(name);
        if (cache != null) {
            cache.clear();
        }
    }

    /**
     * List all caches.
     * @return list of all cache names
     */
    public Collection<String> getAllCaches() {
        return cacheManager.getCacheNames();
    }

    /**
     * Clear all caches.
     */
    public void clearAllCaches() {
        Collection<String> names = cacheManager.getCacheNames();
        for (String name : names) {
            clearCache(name);
        }
    }
}
