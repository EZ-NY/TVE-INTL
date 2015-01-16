package vmn.tve.services.whitelist.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * whitelist root element.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { })
@XmlRootElement(name = "environment")
public class Environment {

    @XmlElement(required = true)
    private ProvidersList providersList;
    private FeaturesList featuresList;
    @XmlAttribute(name = "name", required = true)
    private String name;

    public ProvidersList getProvidersList() {
        return providersList;
    }

    public void setProvidersList(final ProvidersList value) {
        this.providersList = value;
    }

    public FeaturesList getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(final FeaturesList value) {
        this.featuresList = value;
    }

    public String getName() {
        return name;
    }

    public void setName(final String value) {
        this.name = value;
    }

}
