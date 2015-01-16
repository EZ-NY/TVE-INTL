package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ISIS CollectionItems response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionItems {

    @JsonProperty(value = "Items")
    private ImageItems[] imageItems;

    @JsonProperty(value = "CollectionType")
    private CollectionType type;

    public ImageItems[] getImageItems() {
        return imageItems;
    }

    public void setImageItems(final ImageItems[] imageItems) {
        this.imageItems = imageItems;
    }

    public CollectionType getType() {
        return type;
    }

    public void setType(final CollectionType type) {
        this.type = type;
    }

}
