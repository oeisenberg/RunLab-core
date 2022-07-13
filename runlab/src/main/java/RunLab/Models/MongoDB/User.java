package RunLab.Models.MongoDB;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    public String id; 

    public String userName;
    public String pwd;
    public String firstName;
    public String lastName;

    public User() {}

    public User(String userName, String pwd, String firstName, String lastName) {
        this.userName = userName;
        this.pwd = pwd;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, username='%s', firstname='%s', lastname='%s']", id, userName, firstName, lastName);
    }
}
