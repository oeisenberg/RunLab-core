package runlab.controllers;

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

import runlab.models.CodeModel;
import runlab.models.exceptions.InvalidRequest;
import runlab.models.exceptions.UnsupportedAPIException;
import runlab.models.mongoDB.APIDetails;
import runlab.models.mongoDB.User;
import runlab.models.responses.*;
import runlab.repositories.UserRepository;
import runlab.wrappers.*;

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
    public CustomResponse<String> registerAPI(Authentication auth, @RequestBody CodeModel requestBody) throws UnsupportedAPIException, InvalidRequest, IOException {
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
