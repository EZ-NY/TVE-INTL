package vmn.tve.services.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Error returned from provider.
 */
public class Error {
    @JsonProperty("ErrorCode")
    private String errorCode;

    @JsonProperty("Error")
    private String error;

    @JsonProperty("fault")
    private Fault fault;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public Fault getFault() {
        return fault;
    }

    public void setFault(final Fault fault) {
        this.fault = fault;
    }
}

/**
 * Fault that can be returned from provider.
 */
class Fault {
    private String faultstring;
    private Detail detail;

    public String getFaultstring() {
        return faultstring;
    }

    public void setFaultstring(final String faultstring) {
        this.faultstring = faultstring;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(final Detail detail) {
        this.detail = detail;
    }
}

/**
 * Detail error that can be returned from provider.
 */
class Detail {
    private String errorcode;

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(final String errorcode) {
        this.errorcode = errorcode;
    }
}
