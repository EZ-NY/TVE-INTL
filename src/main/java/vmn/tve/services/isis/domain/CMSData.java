package vmn.tve.services.isis.domain;

import java.util.HashMap;
import java.util.Map;

import vmn.tve.services.isis.ImagePlacing;

/**
 * CMS response.
 */
public class CMSData {
    private Map<ImagePlacing, String> images;
    private String displayName;

    /**
     * @param displayName - provider Title
     */
    public CMSData(final String displayName) {
        this.displayName = displayName;
        this.images = new HashMap<ImagePlacing, String>();
    }

    public Map<ImagePlacing, String> getImages() {
        return images;
    }

    public void setImages(final Map<ImagePlacing, String> images) {
        this.images = images;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String providerId) {
        this.displayName = providerId;
    }

    @Override
    public String toString() {
        return "CMSData [images=" + images + ", displayName=" + displayName + "]";
    }

}
