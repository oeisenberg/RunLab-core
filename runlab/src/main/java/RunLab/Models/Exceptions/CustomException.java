package RunLab.models.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    // Serialisable class requires an id
    private static final long serialVersionUID = 1L;
    private final String message;
    private final HttpStatus httpStatus;

    public CustomException(String errorMsg, HttpStatus httpStatus) {
        this.message = errorMsg;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
