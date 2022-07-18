package RunLab.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import RunLab.models.codeModel;
import RunLab.models.exceptions.InvalidRequest;
import RunLab.models.exceptions.UnsupportedAPIException;
import RunLab.models.mongoDB.APIDetails;
import RunLab.models.responses.*;
import RunLab.wrappers.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UnifiedController {
    
    @Autowired
    private APIWrapper apiWrapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    UnifiedController() {}

    // @GetMapping("/oauth")
    // public CustomResponse<String> oauthGET() {
    //     logger.info("Check if Authenticated");
    //     // pull in and update data
    //     boolean isAuthenticated = this.stravaWrapper.refreshAuthTokens();

    //     if (isAuthenticated) {
    //         return new Success<String>();
    //     } else {
    //         return new Failure<String>();
    //     }
    // }

    @PostMapping("/oauth")
    public CustomResponse<String> oauthPOST(@RequestBody codeModel requestBody) throws UnsupportedAPIException, InvalidRequest, IOException {
        logger.info("Authentication Called");
        APIDetails apiDetails = this.apiWrapper.createAPIDetails(requestBody);
        

        return new Success<String>();
    }

}
