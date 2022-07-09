package RunLab.Responces;

import RunLab.Exceptions.InvalidRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class InvalidRequestResponse {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @ResponseBody
    @ExceptionHandler(InvalidRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Failure<String> invalidRequestHandler(InvalidRequest ex) {
        Failure<String> f = new Failure<String>();
        String msg = ex.getMessage();
        logger.error(msg);
        f.setBody(msg);
        return f;
    }
}
