package vmn.tve.services.whitelist.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * whitelist exclusion element.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "exclusion" })
public class Exclusions {

    private List<Exclusion> exclusion;

    /**
     * @return exclusion list for appropriate provider
     */
    public List<Exclusion> getExclusion() {
        if (exclusion == null) {
            exclusion = new ArrayList<Exclusion>();
        }
        return this.exclusion;
    }

    public void setExclusion(final List<Exclusion> exclusion) {
        this.exclusion = exclusion;
    }

}
