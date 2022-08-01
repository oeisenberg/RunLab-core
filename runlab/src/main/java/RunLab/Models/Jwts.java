package RunLab.models;

import java.time.LocalDateTime;

public class Jwts {
    protected String username;
    protected String userID;
    // public T scopes;
    protected String issuer = "http://localhost:8080/runlab-api";
    protected Unix createdDateTime = new Unix(LocalDateTime.now());
    protected Unix expiraryDateTime = new Unix(LocalDateTime.now().plusDays(1)); 


    private Jwts(String username, String id) {
        this.username = username;
        this.userID = id;
    }

    public String getUserName(){
        return this.username;
    }

    public Unix getCreatedDateTime() {
        return this.createdDateTime;
    }

    public Unix getExpiraryDateTime() {
        return this.expiraryDateTime;
    }
}
