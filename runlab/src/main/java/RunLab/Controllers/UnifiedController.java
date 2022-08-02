package RunLab.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import RunLab.models.codeModel;
import RunLab.models.exceptions.InvalidRequest;
import RunLab.models.exceptions.UnsupportedAPIException;
import RunLab.models.mongoDB.APIDetails;
import RunLab.models.mongoDB.User;
import RunLab.models.responses.*;
import RunLab.repositories.UserRepository;
import RunLab.wrappers.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UnifiedController {
    
    @Autowired
    private APIWrapper apiWrapper;

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    UnifiedController() {}

    @PostMapping("/register")
    public CustomResponse<String> registerAPI(Authentication auth, @RequestBody codeModel requestBody) throws UnsupportedAPIException, InvalidRequest, IOException {
        User user = (User) auth.getPrincipal();
        APIDetails apiDetails = this.apiWrapper.createAPIDetails(user, requestBody);
        
        if (apiDetails != null) {
            logger.info("Registering a new API");
            user.updateAPI(apiDetails);
            userRepository.save(user);
        } else {
            logger.error("Registerstration failed, API already exists.");
        }
        
        return new Success<String>();
    }

}
