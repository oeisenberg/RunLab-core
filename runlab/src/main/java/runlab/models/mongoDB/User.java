package runlab.models.mongoDB;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User extends DBdocument {
    @Id
    private String id;
    @Indexed(unique = true, background = true)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private HashMap<APIDetails.API_type, APIDetails> apiDetails = new HashMap<>();

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

    public boolean hasAPI(APIDetails.API_type api_type) {
        return this.apiDetails.containsKey(api_type);
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

    public boolean hasConnectedAPIs() {
        return this.apiDetails != null || this.apiDetails.size() > 0 ? true : false;
    }
    
    public void updateAPI(APIDetails api) {
        this.apiDetails.put(api.getAPIType(), api);
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
