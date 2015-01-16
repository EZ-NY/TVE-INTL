package vmn.tve.services.whitelist.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * whitelist features list element.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "feature" })
@XmlRootElement(name = "featuresList")
public class FeaturesList {

    private List<Feature> feature;

    /**
     * @return feature list
     */
    public List<Feature> getFeature() {
        if (feature == null) {
            feature = new ArrayList<Feature>();
        }
        return this.feature;
    }

    public void setFeature(final List<Feature> feature) {
        this.feature = feature;
    }
}
