package vmn.tve.services.whitelist.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * whitelist exclusion element.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "exclusion")
public class Exclusion {

    @XmlAttribute(name = "platform", required = true)
    private PlatformType platform;
    @XmlAttribute(name = "requestorID", required = true)
    private RequestorIDType requestorID;
    @XmlAttribute(name = "deviceType", required = true)
    private DeviceType deviceType;

    public PlatformType getPlatform() {
        return platform;
    }

    public void setPlatform(final PlatformType value) {
        this.platform = value;
    }

    public RequestorIDType getRequestorID() {
        return requestorID;
    }

    public void setRequestorID(final RequestorIDType value) {
        this.requestorID = value;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(final DeviceType value) {
        this.deviceType = value;
    }

}
