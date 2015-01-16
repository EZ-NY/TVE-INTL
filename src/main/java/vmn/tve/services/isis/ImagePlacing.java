package vmn.tve.services.isis;

/**
 * Picker image is mandatory for config for proper work of any TVE Auth
 * Component. If there is only picker image available then all 3 other image
 * fields should be populated with it. Priority of types (other than picker):
 * default, co-branding, logout.
 */
public enum ImagePlacing {
    // MUST be ordered by descending priority
    PICKER("tve_mvpd_config_picker"),
    DEFAULT("tve_mvpd_config_default"),
    COBRANDING("tve_mvpd_config_cobranding"),
    LOGOUT("tve_mvpd_config_logout");

    private String s;

    private ImagePlacing(String s) {
        this.s = s;
    }

    public String getISISTypeRepresentation() {
        return s;
    }

    /**
     * @param type - ISIS LogogImage representation.
     * @return ImagePlacing instance according to ISIS type.
     */
    public static ImagePlacing getByISISTypeRepresentation(final String type) {
        for (ImagePlacing p : ImagePlacing.values()) {
            if (p.s.equals(type)) {
                return p;
            }
        }
        return null;
    }
}
