package vmn.tve.services.whitelist.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Device types.
 *
 */
@XmlType(name = "deviceType")
@XmlEnum
public enum DeviceType {

    @XmlEnumValue("phone")
    PHONE("phone"),
    @XmlEnumValue("tablet")
    TABLET("tablet"),
    @XmlEnumValue("xbox")
    XBOX("xbox"),
    @XmlEnumValue("tivo")
    TIVO("tivo"),
    @XmlEnumValue("roku")
    ROKU("roku"),
    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("web")
    WEB("web");
    private final String value;

    DeviceType(String v) {
        value = v;
    }

    /**
     * @return device type value
     */
    public String value() {
        return value;
    }

    /**
     * @param v - device type value
     * @return device type
     */
    public static DeviceType fromValue(final String v) {
        for (DeviceType c : DeviceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
