package vmn.tve.providers;

import java.util.HashMap;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import vmn.tve.dto.ProviderDTO;
import vmn.tve.services.errors.LocalServiceException;
import vmn.tve.services.isis.ImagePlacing;

public class ProvidersMergerTest {

    private static final String MAPPING_MVPDID_URL_PREFIX = ".isis.url.image.prefix";
    static final String PROVIDER_URL_IMG_PREFIX = "http://nick-intl.mtvnimages.com/uri/";
    static final String KNOWN_ISIS_LOGO_URL_PREFIX = "mgid:file:gsp:scenic:";
    static final String PICKER_ISIS_URL = KNOWN_ISIS_LOGO_URL_PREFIX + "picker";
    static final String DEFAULT_ISIS_URL = KNOWN_ISIS_LOGO_URL_PREFIX + "default";
    static final String COBRANDING_ISIS_URL = KNOWN_ISIS_LOGO_URL_PREFIX + "cobranding";
    static final String LOGOUT_ISIS_URL = KNOWN_ISIS_LOGO_URL_PREFIX + "logout";

    final static String MVPD_ID = "mvpdid";
    private ProviderDTO providerDTO;

    @InjectMocks
    ProvidersMerger pm;

    @Mock
    Environment env;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeTest
    public void setUp() {
        providerDTO = new ProviderDTO();
        providerDTO.setId(MVPD_ID);
        providerDTO.setAltName("altName");
        providerDTO.setPrimary(true);
        providerDTO.setAuthStandard("OAuth2");
    }

    @Test(expectedExceptions = LocalServiceException.class)
    public void testThrowPickerNotSpecified() {
        // No images specified
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(PROVIDER_URL_IMG_PREFIX);
        pm.populateProviderImages(providerDTO, images);
    }

    @Test(expectedExceptions = LocalServiceException.class)
    public void testNoMappingForLogoURLPrefix() {
        // No images specified
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(null);
        pm.populateProviderImages(providerDTO, images);
    }

    @Test(expectedExceptions = LocalServiceException.class)
    public void testThrowUnknownLogoURLPrefix() {
        final String COBRANDING_UNKNOWN_FORMAT = "unknown:format" + COBRANDING_ISIS_URL;
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        images.put(ImagePlacing.PICKER, PICKER_ISIS_URL);
        images.put(ImagePlacing.COBRANDING, COBRANDING_UNKNOWN_FORMAT);
        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(PROVIDER_URL_IMG_PREFIX);
        pm.populateProviderImages(providerDTO, images);
    }

    @Test
    public void testPopulateAllLogosWithPicker() {
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        images.put(ImagePlacing.PICKER, PICKER_ISIS_URL);

        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(PROVIDER_URL_IMG_PREFIX);
        pm.populateProviderImages(providerDTO, images);

        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getPickerLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getDefaultLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getCobrandingLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getLogoutLogoUrl());
    }

    @Test
    public void testPopulateAllButPickerWithDefault() {
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        images.put(ImagePlacing.PICKER, PICKER_ISIS_URL);
        images.put(ImagePlacing.DEFAULT, DEFAULT_ISIS_URL);

        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(PROVIDER_URL_IMG_PREFIX);
        pm.populateProviderImages(providerDTO, images);

        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getPickerLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + DEFAULT_ISIS_URL, providerDTO.getDefaultLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + DEFAULT_ISIS_URL, providerDTO.getCobrandingLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + DEFAULT_ISIS_URL, providerDTO.getLogoutLogoUrl());
    }

    @Test
    public void testPopulateAllButPickerWithCobranding() {
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        images.put(ImagePlacing.PICKER, PICKER_ISIS_URL);
        images.put(ImagePlacing.COBRANDING, COBRANDING_ISIS_URL);

        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(PROVIDER_URL_IMG_PREFIX);
        pm.populateProviderImages(providerDTO, images);

        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getPickerLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + COBRANDING_ISIS_URL, providerDTO.getDefaultLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + COBRANDING_ISIS_URL, providerDTO.getCobrandingLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + COBRANDING_ISIS_URL, providerDTO.getLogoutLogoUrl());
    }

    @Test
    public void testPopulateAllButPickerWithLogout() {
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        images.put(ImagePlacing.PICKER, PICKER_ISIS_URL);
        images.put(ImagePlacing.LOGOUT, LOGOUT_ISIS_URL);

        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(PROVIDER_URL_IMG_PREFIX);
        pm.populateProviderImages(providerDTO, images);

        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getPickerLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + LOGOUT_ISIS_URL, providerDTO.getDefaultLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + LOGOUT_ISIS_URL, providerDTO.getCobrandingLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + LOGOUT_ISIS_URL, providerDTO.getLogoutLogoUrl());
    }

    @Test
    public void testPopulateMissingWithDefault() {
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        images.put(ImagePlacing.PICKER, PICKER_ISIS_URL);
        images.put(ImagePlacing.DEFAULT, DEFAULT_ISIS_URL);
        images.put(ImagePlacing.LOGOUT, LOGOUT_ISIS_URL);

        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(PROVIDER_URL_IMG_PREFIX);
        pm.populateProviderImages(providerDTO, images);

        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getPickerLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + DEFAULT_ISIS_URL, providerDTO.getDefaultLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + DEFAULT_ISIS_URL, providerDTO.getCobrandingLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + LOGOUT_ISIS_URL, providerDTO.getLogoutLogoUrl());
    }

    @Test
    public void testPopulateMissingWithCobranding() {
        Map<ImagePlacing, String> images = new HashMap<ImagePlacing, String>();
        images.put(ImagePlacing.PICKER, PICKER_ISIS_URL);
        images.put(ImagePlacing.COBRANDING, COBRANDING_ISIS_URL);
        images.put(ImagePlacing.LOGOUT, LOGOUT_ISIS_URL);

        Mockito.when(env.getProperty(MVPD_ID + MAPPING_MVPDID_URL_PREFIX)).thenReturn(PROVIDER_URL_IMG_PREFIX);
        pm.populateProviderImages(providerDTO, images);

        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + PICKER_ISIS_URL, providerDTO.getPickerLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + COBRANDING_ISIS_URL, providerDTO.getDefaultLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + COBRANDING_ISIS_URL, providerDTO.getCobrandingLogoUrl());
        Assert.assertEquals(PROVIDER_URL_IMG_PREFIX + LOGOUT_ISIS_URL, providerDTO.getLogoutLogoUrl());
    }
}
