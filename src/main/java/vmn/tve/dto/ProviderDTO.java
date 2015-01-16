package vmn.tve.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ProviderDTO object converted to json and returned to centralized service.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderDTO {
    private String id = "";
    private String displayName = "";
    private String altName  = "";
    private String authStandard  = "";
    private boolean isPrimary;
    private String defaultLogoUrl  = "";
    private String logoutLogoUrl  = "";
    private String pickerLogoUrl  = "";
    private String cobrandingLogoUrl  = "";

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(final String altName) {
        this.altName = altName;
    }

    public String getAuthStandard() {
        return authStandard;
    }

    public void setAuthStandard(final String authStandard) {
        this.authStandard = authStandard;
    }

    public boolean getIsPrimary() {
        return isPrimary;
    }

    public void setPrimary(final boolean primary) {
        this.isPrimary = primary;
    }

    public String getDefaultLogoUrl() {
        return defaultLogoUrl;
    }

    public void setDefaultLogoUrl(final String defaultLogoUrl) {
        this.defaultLogoUrl = defaultLogoUrl;
    }

    public String getLogoutLogoUrl() {
        return logoutLogoUrl;
    }

    public void setLogoutLogoUrl(final String logoutLogoUrl) {
        this.logoutLogoUrl = logoutLogoUrl;
    }

    public String getPickerLogoUrl() {
        return pickerLogoUrl;
    }

    public void setPickerLogoUrl(final String pickerLogoUrl) {
        this.pickerLogoUrl = pickerLogoUrl;
    }

    public String getCobrandingLogoUrl() {
        return cobrandingLogoUrl;
    }

    public void setCobrandingLogoUrl(final String cobrandingLogoUrl) {
        this.cobrandingLogoUrl = cobrandingLogoUrl;
    }

}
