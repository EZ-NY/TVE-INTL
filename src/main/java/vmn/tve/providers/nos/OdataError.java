package vmn.tve.providers.nos;

/**
 * OdataError.
 */
public class OdataError {
    private String code;
    private Message message;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(final Message message) {
        this.message = message;
    }
}

/**
 * Message - information if user is subscribed for appropriate channel.
 */
class Message {
    private String lang;
    private String value;

    public String getLang() {
        return lang;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

}
