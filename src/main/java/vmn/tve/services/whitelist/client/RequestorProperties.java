package vmn.tve.services.whitelist.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * whitelist RequestorProperties element.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "property" })
@XmlRootElement(name = "requestorProperties")
public class RequestorProperties {

    private List<Property> property;
    @XmlAttribute(name = "requestorID", required = true)
    private String requestorID;

    /**
     * @return list of properties
     */
    public List<Property> getProperty() {
        if (property == null) {
            property = new ArrayList<Property>();
        }
        return this.property;
    }

    public String getRequestorID() {
        return requestorID;
    }

    public void setRequestorID(final String value) {
        this.requestorID = value;
    }

    public void setProperty(final List<Property> property) {
        this.property = property;
    }
}
