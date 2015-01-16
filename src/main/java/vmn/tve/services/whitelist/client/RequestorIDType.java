package vmn.tve.services.whitelist.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * RequestorIDType.
 *
 */
/**
 * @author Mykhaylo Hrytsiv <mhrytsiv@epam.com>
 *
 */
/**
 * @author Mykhaylo Hrytsiv <mhrytsiv@epam.com>
 *
 */
@XmlType(name = "requestorIDType")
@XmlEnum
public enum RequestorIDType {

    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("comedycentral")
    COMEDYCENTRAL("comedycentral"),
    @XmlEnumValue("tvland")
    TVLAND("tvland"),
    @XmlEnumValue("palladia")
    PALLADIA("palladia"),
    @XmlEnumValue("mtv")
    MTV("mtv"),
    @XmlEnumValue("mtv2")
    MTV_2("mtv2"),
    @XmlEnumValue("vh1")
    VH_1("vh1"),
    @XmlEnumValue("cmt")
    CMT("cmt"),
    @XmlEnumValue("logo")
    LOGO("logo"),
    @XmlEnumValue("nick")
    NICK("nick"),
    @XmlEnumValue("nickjr")
    NICKJR("nickjr"),
    @XmlEnumValue("spike")
    SPIKE("spike"),
    @XmlEnumValue("bet")
    BET("bet"),
    @XmlEnumValue("mtv-xbox")
    MTV_XBOX("mtv-xbox"),
    @XmlEnumValue("nick-xbox")
    NICK_XBOX("nick-xbox"),
    @XmlEnumValue("nickjr-xbox")
    NICKJR_XBOX("nickjr-xbox");
    private final String value;

    RequestorIDType(String v) {
        value = v;
    }

    /**
     * @return value
     */
    public String value() {
        return value;
    }

    /**
     * @param v - value
     * @return - requestor id type
     */
    public static RequestorIDType fromValue(final String v) {
        for (RequestorIDType c : RequestorIDType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
