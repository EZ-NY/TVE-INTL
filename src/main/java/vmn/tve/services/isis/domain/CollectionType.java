package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ISIS CollectionType response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionType {
    @JsonProperty(value = "TypeName")
    private String typeName;
}
