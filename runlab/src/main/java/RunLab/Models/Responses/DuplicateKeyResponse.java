package RunLab.Models.Responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mongodb.MongoWriteException;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class DuplicateKeyResponse {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    @ResponseBody
    @ExceptionHandler(MongoWriteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Failure<String> invalidRequestHandler(MongoWriteException ex) {
        Failure<String> f = new Failure<String>();
        ex.printStackTrace(pw);
        logger.error(ex.getMessage());
        f.setMessage(ex.getMessage());
        return f;
    }
}
