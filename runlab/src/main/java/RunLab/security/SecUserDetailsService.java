package RunLab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import RunLab.Models.SecUserDetails;
import RunLab.Models.MongoDB.User;
import RunLab.Repositories.UserRepository;

// A seperate service to be injected inside the AuthenticationManagerBuilder.
// https://stackoverflow.com/questions/29606290/authentication-with-spring-security-spring-data-mongodb
@Component
public class SecUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }  else {
            UserDetails details = new SecUserDetails(user);
            return details;
        }
    }
}
