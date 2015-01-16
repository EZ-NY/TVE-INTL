package vmn.tve.services.whitelist.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ProvidersList.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "provider" })
@XmlRootElement(name = "providersList")
public class ProvidersList {

    @XmlElement(required = true)
    private List<Provider> provider;

    /**
     * @return provider list
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
