package RunLab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// https://www.devglan.com/spring-security/spring-boot-jwt-auth
// https://www.bezkoder.com/spring-boot-mongodb-login-example/
// https://github.com/murraco/spring-boot-jwt
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecUserDetailsService userDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new AuthenticationFilter();
    }

    @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        builder.inMemoryAuthentication().withUser("username").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disables Cross Origin Resource Sharing and Cross Site Request Forgery
        http.cors().and().csrf().disable();
        
        // No session will be created or used by Spring Security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Pattern match for my entry points and disallow anything else
        http.authorizeRequests()
            .antMatchers("/v{\\d+}/token/*").permitAll()
            .antMatchers("/v{\\d+}/token/*", "/signup").permitAll()
            .anyRequest().authenticated();
            
        // Redirects a user if they don't have enough permissions
        http.exceptionHandling().accessDeniedPage("/login");
        
        // Optional: allows testing from a web browser.
        http.httpBasic();

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

}
