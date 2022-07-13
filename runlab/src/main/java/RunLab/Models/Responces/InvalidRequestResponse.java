package RunLab.Models.Responces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import RunLab.Models.Exceptions.InvalidRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class InvalidRequestResponse {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @ResponseBody
    @ExceptionHandler(InvalidRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Failure<Throwable> invalidRequestHandler(InvalidRequest ex) {
        Failure<Throwable> f = new Failure<Throwable>();
        logger.error(ex.getMessage() + " due to " + ex.getCause());
        f.setBody(ex.getCause());
        return f;
    }
}
