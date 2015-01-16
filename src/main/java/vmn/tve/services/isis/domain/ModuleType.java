package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ISIS ModuleType response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleType {
    @JsonProperty(value = "mtvi:urlKey")
    private String urlKey;

    @JsonProperty(value = "TypeName")
    private String typeName;

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(final String urlKey) {
        this.urlKey = urlKey;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }
}
