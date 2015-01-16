package vmn.tve.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vmn.tve.services.cache.CacheService;

/**
 * Tool to clear cache on appropriate nodes related to load balancer.
 */
@Controller
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Collection<String> getAllCaches() {
        return cacheService.getAllCaches();
    }

    /**
     * @param cacheName - name of specific cache that has to be cleared
     * @return successful message
     */
    @RequestMapping(value = "/clear/{cacheName}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String clearCache(@PathVariable("cacheName") final String cacheName) {
        cacheService.clearCache(cacheName);
        return "{ Cache cleared }";
    }

    /**
     * @return successful message
     */
    @RequestMapping(value = "/clearAllCaches", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String clearAllCaches() {
        cacheService.clearAllCaches();
        return "{ Caches cleared }";
    }
}
