package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ISIS ImageItems response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageItems {

    @JsonProperty(value = "mtvi:urlKey")
    private String urlKey;

    @JsonProperty(value = "Title")
    private String title;

    @JsonProperty(value = "ImageAssetRefs")
    private ImageAssetRefs[] imageAssetRefs;

    public ImageAssetRefs[] getImageAssetRefs() {
        return imageAssetRefs;
    }

    public void setImageAssetRefs(final ImageAssetRefs[] imageAssetRefs) {
        this.imageAssetRefs = imageAssetRefs;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(final String urlKey) {
        this.urlKey = urlKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}
