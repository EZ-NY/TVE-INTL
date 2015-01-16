package vmn.tve.services.errors;

import org.springframework.http.HttpStatus;

/**
 * LocalServiceException class intended to fill with detail info about errors.
 */
public class LocalServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ResponseGroup responseGroup;
    private ResponseCode responseCode;
    private HttpStatus statusCode;

    /**
     * @param errorMessage - response with error that has been received from
     *            provider.
     */
    public LocalServiceException(final String errorMessage) {
        super(errorMessage);
    }

    /**
     * @param responseGroup - group that is returned to centralized service.
     * @param responseCode - error code that is returned to centralized service.
     */
    public LocalServiceException(final ResponseGroup responseGroup, final ResponseCode responseCode) {
        this.responseGroup = responseGroup;
        this.responseCode = responseCode;
    }

    /**
     * @param responseGroup - group that is returned to centralized service.
     * @param responseCode - error code that is returned to centralized service.
     * @param statusCode - http status code that is returned to centralized
     *            service.
     */
    public LocalServiceException(final ResponseGroup responseGroup, final ResponseCode responseCode,
            final HttpStatus statusCode) {
        this.responseGroup = responseGroup;
        this.responseCode = responseCode;
        this.statusCode = statusCode;
    }

    public ResponseGroup getResponseGroup() {
        return responseGroup;
    }

    public void setResponseGroup(final ResponseGroup responseGroup) {
        this.responseGroup = responseGroup;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(final ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

}
