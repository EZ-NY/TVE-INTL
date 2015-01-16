package vmn.tve.services.isis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ISIS Response response tag.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private Docs[] docs;

    public Docs[] getDocs() {
        return docs;
    }

    public void setDocs(final Docs[] docs) {
        this.docs = docs;
    }

}
