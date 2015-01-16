package vmn.tve.services.errors;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * error mapping that is specified in errormapping.xml file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "provider" })
@XmlRootElement(name = "errorMapping")
public class ErrorMapping {

    private List<Provider> provider;

    /**
     * @return provider list that are defined error mapping in file.
     */
    public List<Provider> getProvider() {
        if (provider == null) {
            provider = new ArrayList<Provider>();
        }
        return this.provider;
    }

    public void setProvider(final List<Provider> provider) {
        this.provider = provider;
    }
}

/**
 * error mapping for appropriate provider configured errormapping.xml file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "errorCode" })
class Provider {

    @XmlElement(name = "ErrorCode")
    private List<ErrorCode> errorCode;
    @XmlAttribute(name = "id")
    private String id;

    public List<ErrorCode> getErrorCode() {
        if (errorCode == null) {
            errorCode = new ArrayList<ErrorCode>();
        }
        return this.errorCode;
    }

    public void setErrorCode(final List<ErrorCode> errorCode) {
        this.errorCode = errorCode;
    }

    public String getId() {
        return id;
    }

    public void setId(final String value) {
        this.id = value;
    }
}

/**
 * provider error code mapped for code related to centralized service errors.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "group", "csErrorCode", "statusCode" })
class ErrorCode {

    @XmlElement(name = "Group", required = true)
    private String group;
    @XmlElement(name = "CSErrorCode", required = true)
    private String csErrorCode;
    @XmlElement(name = "StatusCode")
    private short statusCode;
    @XmlAttribute(name = "code")
    private String code;

    public String getGroup() {
        return group;
    }

    public void setGroup(final String value) {
        this.group = value;
    }

    public String getCSErrorCode() {
        return csErrorCode;
    }

    public void setCSErrorCode(final String value) {
        this.csErrorCode = value;
    }

    public short getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final short value) {
        this.statusCode = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String value) {
        this.code = value;
    }

    public String getCsErrorCode() {
        return csErrorCode;
    }

    public void setCsErrorCode(final String csErrorCode) {
        this.csErrorCode = csErrorCode;
    }
}
