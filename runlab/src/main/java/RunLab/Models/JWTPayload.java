package RunLab.models;

import java.time.LocalDateTime;
import RunLab.utility.UnixConverter;

public class JWTPayload {
    protected String username;
    protected String userID;
    // public T scopes;
    protected String issuer = "http://localhost:8080/runlab-api";
    protected long createdDateTime = UnixConverter.toUnix(LocalDateTime.now());
    protected long expiraryDateTime = UnixConverter.toUnix(LocalDateTime.now().plusDays(1)); 


    public JWTPayload(String username, String id) {
        this.username = username;
        this.userID = id;
    }

    public String getUserName(){
        return this.username;
    }

    public LocalDateTime getCreatedDateTime() {
        return UnixConverter.toDateTime(this.createdDateTime);
    }

    public LocalDateTime getExpiraryDateTime() {
        return UnixConverter.toDateTime(this.expiraryDateTime);
    }
}
