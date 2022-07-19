package RunLab.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import RunLab.models.mongoDB.User;

public class SecUserDetails implements UserDetails {
    private User user;

    public SecUserDetails(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: Add basic roles?
        // https://stackoverflow.com/questions/7737602/example-of-custom-implementation-of-userdetails
        return null;
    }

    @Override
    public boolean isEnabled() {
        // return this.user.isEnabled();
        return true;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        // return this.user.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // return this.user.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // return this.user.isCredentialsNonExpired();
        return true;
    }
}
