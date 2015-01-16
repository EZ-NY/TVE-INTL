package vmn.tve.services.whitelist.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * PlatformType.
 *
 */
@XmlType(name = "platformType")
@XmlEnum
public enum PlatformType {

    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("ios")
    IOS("ios"),
    @XmlEnumValue("android")
    ANDROID("android"),
    @XmlEnumValue("windows")
    WINDOWS("windows"),
    @XmlEnumValue("console")
    CONSOLE("console"),
    @XmlEnumValue("html5")
    HTML_5("html5"),
    @XmlEnumValue("flash")
    FLASH("flash");
    private final String value;

    PlatformType(String v) {
        value = v;
    }

    /**
     * @return platform type value
     */
    public String value() {
        return value;
    }

    /**
     * @param v - platform type value
     * @return - platform type
     */
    public static PlatformType fromValue(final String v) {
        for (PlatformType c : PlatformType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
