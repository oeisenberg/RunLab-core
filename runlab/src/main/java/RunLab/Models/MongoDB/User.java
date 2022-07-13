package RunLab.Models.MongoDB;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User extends mongodbDocument {
    @Id
    public String id; 
    @Indexed(unique=true, background = true)
    public String userName;
    private String password;
    public String firstName;
    public String lastName;
    public List<thirdpartyAPIDetails> apiDetails;

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
}
