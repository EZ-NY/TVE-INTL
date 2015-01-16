package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ISIS ModuleItems response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleItems {

    @JsonProperty(value = "Items")
    private CollectionItems[] collectionItems;
    @JsonProperty(value = "ModuleType")
    private ModuleType type;

    public CollectionItems[] getCollectionItems() {
        return collectionItems;
    }

    public void setCollectionItems(final CollectionItems[] items) {
        this.collectionItems = items;
    }

    public ModuleType getType() {
        return type;
    }

    public void setType(final ModuleType type) {
        this.type = type;
    }
}
