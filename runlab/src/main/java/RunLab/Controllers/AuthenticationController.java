package runlab.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import runlab.repositories.UserRepository;
import runlab.utility.jwtTokenUtil;
import runlab.wrappers.APIWrapper;
import runlab.models.LoginUser;
import runlab.models.RegisteringUser;
import runlab.models.mongoDB.APIDetails;
import runlab.models.mongoDB.User;
import runlab.models.responses.CustomResponse;
import runlab.models.responses.Success;

@RestController
@RequestMapping("/v1/token")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private APIWrapper apiWrapper;

    @Autowired
    private jwtTokenUtil jwtUtil;

    @PostMapping("/signup")
    public User saveUser(@RequestBody RegisteringUser user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        return userRepository.save(newUser);
    }

    @PostMapping("/generate-token")
    public CustomResponse<String> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        final User user = userRepository.findByUserName(loginUser.getUsername());
        userRepository.save(refreshAPITokens(user));
        Success<String> response = new Success<String>(); 
        response.setBody(jwtUtil.createToken(user));
        return response;
    }

    private User refreshAPITokens(User user) {
        // refreshes regardless, perhaps should check if expired before login & each req instead?
        for (APIDetails api : user.getAPIDetails()) { // (new user this is null)
            user.updateAPI(apiWrapper.refreshAPIDetails(api));
        }
        return user;
    }
}
