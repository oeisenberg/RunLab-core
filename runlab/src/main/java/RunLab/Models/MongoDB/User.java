package RunLab.Models.MongoDB;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user")
public class User {
    @Id
    private String id; 
    @Indexed(unique=true, background = true)
    public String userName;
    private String password;
    public String firstName;
    public String lastName;
    public LocalDateTime createdDateTime = LocalDateTime.now();

    public User() {}

    public User(String userName, String pwd, String firstName, String lastName) {
        this.userName = userName;
        this.password = pwd;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, username='%s', firstname='%s', lastname='%s']", id, userName, firstName, lastName);
    }
}
