package vmn.tve.services.whitelist.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * ObjectFactory.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName EXCLUSIONS_QNAME = new QName("", "exclusions");

    /**
     * ObjectFactory constructor.
     */
    public ObjectFactory() {
    }

    /**
     * @return created platform
     */
    public Platform createPlatform() {
        return new Platform();
    }

    /**
     * @return created requestor properties
     */
    public RequestorProperties createRequestorProperties() {
        return new RequestorProperties();
    }

    /**
     * @return created property
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * @return created providers list
     */
    public ProvidersList createProvidersList() {
        return new ProvidersList();
    }

    /**
     * @return created provider
     */
    public Provider createProvider() {
        return new Provider();
    }

    /**
     * @return created exclusions
     */
    public Exclusions createExclusions() {
        return new Exclusions();
    }

    /**
     * @return created environment
     */
    public Environment createEnvironment() {
        return new Environment();
    }

    /**
     * @return created features list
     */
    public FeaturesList createFeaturesList() {
        return new FeaturesList();
    }

    /**
     * @return created feature
     */
    public Feature createFeature() {
        return new Feature();
    }

    /**
     * @return created exclusion
     */
    public Exclusion createExclusion() {
        return new Exclusion();
    }

    /**
     * @param value - exclusions
     * @return jaxb element exclusions
     */
    @XmlElementDecl(namespace = "", name = "exclusions")
    public JAXBElement<Exclusions> createExclusions(final Exclusions value) {
        return new JAXBElement<Exclusions>(EXCLUSIONS_QNAME, Exclusions.class, null, value);
    }

}
