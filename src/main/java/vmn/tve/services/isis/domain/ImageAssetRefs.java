package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ISIS ImageAssetRefs response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageAssetRefs {
    @JsonProperty(value = "URI")
    private String uri;

    public String getURI() {
        return uri;
    }

    public void setURI(final String url) {
        this.uri = url;
    }
}
