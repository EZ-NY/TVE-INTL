package vmn.tve.providers.nos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UserSubscribedChannel object returned from provider.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSubscribedChannel {

    @JsonProperty("odata.error")
    private OdataError odataError;

    @JsonProperty("odata.metadata")
    private String odataMetadata;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("AssetId")
    private String assetId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("ChannelId")
    private String channelId;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("ImageUrl")
    private String imageUrl;

    @JsonProperty("PosterUrl")
    private String posterUrl;

    @JsonProperty("ImageFile")
    private String imageFile;

    @JsonProperty("PosterFile")
    private String posterFile;

    @JsonProperty("Position")
    private Integer position;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Rating")
    private String rating;

    @JsonProperty("DvbLocator")
    private String dvbLocator;

    @JsonProperty("IsOnline")
    private Boolean isOnline;

    public String getOdataMetadata() {
        return odataMetadata;
    }

    public void setOdataMetadata(final String odataMetadata) {
        this.odataMetadata = odataMetadata;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(final String assetId) {
        this.assetId = assetId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(final String channelId) {
        this.channelId = channelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(final String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(final String imageFile) {
        this.imageFile = imageFile;
    }

    public String getPosterFile() {
        return posterFile;
    }

    public void setPosterFile(final String posterFile) {
        this.posterFile = posterFile;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(final Integer position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(final String rating) {
        this.rating = rating;
    }

    public String getDvbLocator() {
        return dvbLocator;
    }

    public void setDvbLocator(final String dvbLocator) {
        this.dvbLocator = dvbLocator;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(final Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public OdataError getOdataError() {
        return odataError;
    }

    public void setOdataError(final OdataError odataError) {
        this.odataError = odataError;
    }
}
