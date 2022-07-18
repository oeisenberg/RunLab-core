package RunLab.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import RunLab.repositories.UserRepository;
import RunLab.utility.jwtTokenUtil;
import RunLab.models.LoginUser;
import RunLab.models.RegisteringUser;
import RunLab.models.mongoDB.APIDetails;
import RunLab.models.mongoDB.User;
import RunLab.models.responses.CustomResponse;
import RunLab.models.responses.Success;

@RestController
@RequestMapping("/v1/token")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

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
        refreshAPITokens(user);
        Success<String> response = new Success<String>(); 
        response.setBody(jwtTokenUtil.encodeToToken(user));
        return response;
    }

    private void refreshAPITokens(User user) {
        // for all apis in User api list
            // check if needs to be refreshed
            // refresh
        for (APIDetails api : user.getAPIs().values()) {
            api.
        }
    }
}
