package vmn.tve.config.cache;

/**
 * Cache property described ttl and maximum size of objects that can be put to cache.
 *
 */
public class CacheProperty {
    private Integer timeToLive;
    private Integer maximumSize;

    public Integer getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(final Integer timeToLive) {
        this.timeToLive = timeToLive;
    }

    public Integer getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(final Integer maximumSize) {
        this.maximumSize = maximumSize;
    }
}
