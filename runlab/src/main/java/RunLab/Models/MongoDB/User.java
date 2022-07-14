package RunLab.Models.MongoDB;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User extends mongodbDocument {
    @Id
    protected String id; 
    @Indexed(unique=true, background = true)
    protected String userName;
    private String password;
    protected String firstName;
    protected String lastName;
    protected List<thirdpartyAPIDetails> apiDetails;

    public User() {}

    public User(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, username='%s', firstname='%s', lastname='%s']", id, userName, firstName, lastName);
    }

    public String getUserID(){
        return this.id;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String username){
        this.userName = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
}
