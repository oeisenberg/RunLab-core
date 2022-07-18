package RunLab.models.mongoDB;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User extends mongodbDocument {
    @Id
    private String id;
    @Indexed(unique = true, background = true)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private HashMap<APIDetails.API_type, APIDetails> apiDetails;

    private boolean isEnabled = true;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;

    public User() {
    }

    public User(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, username='%s', firstname='%s', lastname='%s']", id, userName, firstName,
                lastName);
    }

    public String getUserID() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public APIDetails getAPI(APIDetails.API_type api_type) {
        return this.apiDetails.get(api_type);
    }

    public APIDetails removeAPI(APIDetails.API_type api_type) {
        return this.apiDetails.remove(api_type);
    }

    public Collection<APIDetails> getAPIDetails() {
        return this.apiDetails.values();
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void updateAPI(APIDetails api) {
        this.apiDetails.replace(api.getAPIType(), api);
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
