package vmn.tve.services.whitelist.client;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Property.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "property")
public class Property {

    @XmlAttribute(name = "duration")
    private BigInteger duration;
    @XmlAttribute(name = "isActive")
    private Boolean isActive;

    /**
     * @return duration
     */
    public BigInteger getDuration() {
        if (duration == null) {
            return new BigInteger("86400");
        } else {
            return duration;
        }
    }

    public void setDuration(final BigInteger value) {
        this.duration = value;
    }

    /**
     * @return isActive
     */
    public boolean isIsActive() {
        if (isActive == null) {
            return false;
        } else {
            return isActive;
        }
    }

    public void setIsActive(final Boolean value) {
        this.isActive = value;
    }

    public Boolean getIsActive() {
        return isActive;
    }

}
