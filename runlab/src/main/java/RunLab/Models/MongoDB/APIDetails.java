package RunLab.models.mongoDB;

import java.time.Instant;

import RunLab.models.Unix;

public class APIDetails extends mongodbDocument {
    public String name;
    public String tokenType;
    public Unix expiresAt;
    public Unix expiresIn;

    private String authenticationCode;
    private String refreshCode;

    public boolean hasExpired() {
        return this.expiresAt.isGreaterThan(Instant.now().getEpochSecond()) ? true : false;
    }
}
