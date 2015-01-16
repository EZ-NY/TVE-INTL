package vmn.tve.services.whitelist.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * whitelist feature element.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "platform" })
@XmlRootElement(name = "feature")
public class Feature {

    @XmlElement(required = true)
    private List<Platform> platform;
    @XmlAttribute(name = "name", required = true)
    private String name;

    /**
     * @return platform list
     */
    public List<Platform> getPlatform() {
        if (platform == null) {
            platform = new ArrayList<Platform>();
        }
        return this.platform;
    }

    public String getName() {
        return name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    public void setPlatform(final List<Platform> platform) {
        this.platform = platform;
    }

}
