package vmn.tve.services.whitelist;

import static org.mockito.Mockito.when;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import vmn.tve.services.whitelist.client.Environment;
import vmn.tve.services.whitelist.client.Provider;
import vmn.tve.services.whitelist.parser.WhiteListParser;

public class WhiteListServiceTest {
    private final static String WHITELIST_EXCLUSIONS = "whitelist_exclusions.xml";

    @InjectMocks
    private WhiteListService whiteListService;

    @Mock
    private WhiteListParser whiteListParser;

    @Mock
    private ApplicationContext context;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(context.getBean("WhiteListParser")).thenReturn(whiteListParser);
    }

    @Test
    public void testWhiteListExclusionsFirst() {
        Environment environment = parseWhitelist(WhiteListServiceTest.class.getResource(WHITELIST_EXCLUSIONS).getPath());
        when(whiteListParser.parse(Mockito.anyString())).thenReturn(environment);
        List<Provider> providers = whiteListService.getProviders("pt_PT", "android", "nick", "tablet");
        Assert.assertNotNull(providers);
        Assert.assertEquals(providers.size(), 7);
        Assert.assertEquals(providers.get(0).getId(), "DTV");
        Assert.assertEquals(providers.get(1).getId(), "TWC");
        Assert.assertEquals(providers.get(2).getId(), "auth_armstrongmywire_com");
        Assert.assertEquals(providers.get(3).getId(), "ATMC");
        Assert.assertEquals(providers.get(4).getId(), "consolidated_auth-gateway_net");
        Assert.assertEquals(providers.get(5).getId(), "ANTIETAM");
        Assert.assertEquals(providers.get(6).getId(), "Bend");
    }

    @Test
    public void testWhiteListExclusionsSecond() {
        Environment environment = parseWhitelist(WhiteListServiceTest.class.getResource(WHITELIST_EXCLUSIONS).getPath());
        when(whiteListParser.parse(Mockito.anyString())).thenReturn(environment);
        List<Provider> providers = whiteListService.getProviders("pt_PT", "android", "nick", "phone");
        Assert.assertNotNull(providers);
        Assert.assertEquals(providers.size(), 4);
        Assert.assertEquals(providers.get(0).getId(), "DTV");
        Assert.assertEquals(providers.get(1).getId(), "TWC");
        Assert.assertEquals(providers.get(2).getId(), "auth_armstrongmywire_com");
        Assert.assertEquals(providers.get(3).getId(), "Bend");
    }

    public Environment parseWhitelist(String resource) {
        Environment environment = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(resource);

            JAXBContext jaxbContext = JAXBContext.newInstance(Environment.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            environment = (Environment) jaxbUnmarshaller.unmarshal(doc);
        } catch (Exception e) {
            Assert.fail("Required resource has not been found: " + resource, e);
        }
        return environment;
    }
}
