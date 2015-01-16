package vmn.tve.services.isis;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import vmn.tve.services.isis.domain.CMSData;

import com.google.common.base.Charsets;

public class ISISServiceTest {
    private final static String ISIS_RESPONSE = "isis_response.txt";

    @InjectMocks
    private ISISService isisService;

    @Mock
    org.springframework.core.env.Environment env;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuildUrlToISISService() {
        String urlTemplate = "http://squid.jp.mtvnservices.com/jp/{isis_namespace}?q=%7Bselect:%7B%22*%22:%7B%22*%22:%7B%22*%22:%7B%22*%22:%7B%22*%22:"
                + "%7B%22*%22:%7B%22*%22:%7B%22*%22:1%7D%7D%7D%7D%7D%7D%7D%7D,"
                + "%20where:%7BbyTypeAndUrlKey:[%22Site:GenericModule%22,%20%22tve_config_{brand}_{platform}%22]}}&stage={stage}&dpPlatforms={dpPlatform}";

        String expectedUrl = "http://squid.jp.mtvnservices.com/jp/nickelodeon.pt?q=%7Bselect:%7B%22*%22:%7B%22*%22:%7B%22*%22:%7B%22*%22:%7B%22*%22:"
                + "%7B%22*%22:%7B%22*%22:%7B%22*%22:1%7D%7D%7D%7D%7D%7D%7D%7D,%20where:%7BbyTypeAndUrlKey:[%22Site:GenericModule%22,%20%22tve_config_nick_android%22]}}"
                + "&stage=authoring&dpPlatforms=0dc9bf65-5ac0-42e7-9be4-90ef5967e40d,1db04516-46df-404e-bcd1-ef04c6550eca";

        when(env.getProperty("isis.url.template")).thenReturn(urlTemplate);
        when(env.getProperty("isis.stage")).thenReturn("authoring");
        when(env.getProperty("isis.nick.pt")).thenReturn("nickelodeon.pt");

        String createdUrl = isisService.buildURL("pt", "nick", "android");
        Assert.assertNotNull(createdUrl);
        Assert.assertEquals(expectedUrl, createdUrl);
    }

    @Test
    public void testParseISISResponse() {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(getISISResponseJSON(ISIS_RESPONSE), writer, Charsets.UTF_8.name());
            Map<String, CMSData> isisData = isisService.parseResponse(writer.toString());
            Assert.assertNotNull(isisData);
            CMSData cmsData = isisData.get("nos");
            Assert.assertNotNull(cmsData);
            Assert.assertNotNull(cmsData.getDisplayName());
            Map<ImagePlacing, String> images = cmsData.getImages();
            Assert.assertNotNull(images);
            Assert.assertEquals(images.size(), 2);
            String pickerChunkUrl = images.get(ImagePlacing.PICKER);
            String cobrandingChunkUrl = images.get(ImagePlacing.COBRANDING);
            Assert.assertNotNull(pickerChunkUrl);
            Assert.assertNotNull(cobrandingChunkUrl);
            Assert.assertEquals("mgid:file:gsp:scenic:/international/nickelodeon.pt/tve_mvpd_nos_black.png",
                    pickerChunkUrl);
            Assert.assertEquals("mgid:file:gsp:scenic:/international/nickelodeon.pt/tve_mvpd_nos_black.png",
                    cobrandingChunkUrl);

        } catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    private InputStream getISISResponseJSON(String fileName) throws FileNotFoundException {
        return new FileInputStream(new File(ISISServiceTest.class.getResource(fileName).getPath()));
    }

}
