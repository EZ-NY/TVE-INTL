package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ISIS Docs response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Docs {
    @JsonProperty(value = "Items")
    private ModuleItems[] moduleItems;

    public ModuleItems[] getModuleItems() {
        return moduleItems;
    }

    public void setModuleItems(final ModuleItems[] items) {
        this.moduleItems = items;
    }

}
