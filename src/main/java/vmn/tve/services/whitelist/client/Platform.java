package vmn.tve.services.whitelist.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Platform.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "requestorProperties" })
@XmlRootElement(name = "platform")
public class Platform {

    private List<RequestorProperties> requestorProperties;
    @XmlAttribute(name = "name", required = true)
    private PlatformType name;

    /**
     * @return list of requestor properties
     */
    public List<RequestorProperties> getRequestorProperties() {
        if (requestorProperties == null) {
            requestorProperties = new ArrayList<RequestorProperties>();
        }
        return this.requestorProperties;
    }

    public PlatformType getName() {
        return name;
    }

    public void setName(final PlatformType value) {
        this.name = value;
    }

    public void setRequestorProperties(final List<RequestorProperties> requestorProperties) {
        this.requestorProperties = requestorProperties;
    }
}
