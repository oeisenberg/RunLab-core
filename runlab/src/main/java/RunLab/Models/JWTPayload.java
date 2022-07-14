package RunLab.models;

import java.time.LocalDateTime;

public class JWTPayload {
    protected String username;
    protected String userID;
    // public T scopes;
    protected String issuer = "http://localhost:8080/runlab-api";
    public LocalDateTime createdDateTime = LocalDateTime.now();
    protected LocalDateTime expiraryDateTime = LocalDateTime.now().plusDays(1); 


    public JWTPayload(String username, String id) {
        this.username = username;
        this.userID = id;
    }

    public String getUserName(){
        return this.username;
    }

    public LocalDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    public LocalDateTime getExpiraryDateTime() {
        return this.expiraryDateTime;
    }
}
