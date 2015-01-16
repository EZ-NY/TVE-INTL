package vmn.tve.services.whitelist.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * Provider.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "exclusions" })
@XmlRootElement(name = "provider")
public class Provider {

    @XmlElement(nillable = true)
    private List<Exclusions> exclusions;
    @XmlAttribute(name = "id", required = true)
    private String id;
    @XmlAttribute(name = "altName", required = true)
    private String altName;
    @XmlAttribute(name = "serviceManager", required = true)
    private String serviceManager;
    @XmlAttribute(name = "freePreviewAvailable")
    private Boolean freePreviewAvailable;
    @XmlAttribute(name = "liveStreamAvailable")
    private Boolean liveStreamAvailable;
    @XmlAttribute(name = "allowWMS")
    private Boolean allowWMS;
    @XmlAttribute(name = "liveStreamTTL")
    @XmlSchemaType(name = "positiveInteger")
    private BigInteger liveStreamTTL;
    @XmlAttribute(name = "isFullPageRedirect")
    private Boolean isFullPageRedirect;

    /**
     * @return exclusions list
     */
    public List<Exclusions> getExclusions() {
        if (exclusions == null) {
            exclusions = new ArrayList<Exclusions>();
        }
        return this.exclusions;
    }

    public String getId() {
        return id;
    }

    public void setId(final String value) {
        this.id = value;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(final String value) {
        this.altName = value;
    }

    public String getServiceManager() {
        return serviceManager;
    }

    public void setServiceManager(final String value) {
        this.serviceManager = value;
    }

    public Boolean isFreePreviewAvailable() {
        return freePreviewAvailable;
    }

    public void setFreePreviewAvailable(final Boolean value) {
        this.freePreviewAvailable = value;
    }

    /**
     * @return isLiveStreamAvailable
     */
    public boolean isLiveStreamAvailable() {
        if (liveStreamAvailable == null) {
            return false;
        } else {
            return liveStreamAvailable;
        }
    }

    public void setLiveStreamAvailable(final Boolean value) {
        this.liveStreamAvailable = value;
    }

    /**
     * @return isAllowWMS
     */
    public boolean isAllowWMS() {
        if (allowWMS == null) {
            return true;
        } else {
            return allowWMS;
        }
    }

    public void setAllowWMS(final Boolean value) {
        this.allowWMS = value;
    }

    /**
     * @return getLiveStreamTTL
     */
    public BigInteger getLiveStreamTTL() {
        if (liveStreamTTL == null) {
            return new BigInteger("86400");
        } else {
            return liveStreamTTL;
        }
    }

    public void setLiveStreamTTL(final BigInteger value) {
        this.liveStreamTTL = value;
    }

    /**
     * @return isIsFullPageRedirect
     */
    public boolean isIsFullPageRedirect() {
        if (isFullPageRedirect == null) {
            return false;
        } else {
            return isFullPageRedirect;
        }
    }

    public void setIsFullPageRedirect(final Boolean value) {
        this.isFullPageRedirect = value;
    }

    public Boolean getFreePreviewAvailable() {
        return freePreviewAvailable;
    }

    public Boolean getLiveStreamAvailable() {
        return liveStreamAvailable;
    }

    public Boolean getAllowWMS() {
        return allowWMS;
    }

    public Boolean getIsFullPageRedirect() {
        return isFullPageRedirect;
    }

    public void setExclusions(final List<Exclusions> exclusions) {
        this.exclusions = exclusions;
    }

}
