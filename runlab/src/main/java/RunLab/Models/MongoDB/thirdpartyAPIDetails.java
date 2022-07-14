package RunLab.models.mongoDB;

public class thirdpartyAPIDetails extends mongodbDocument {
    public String name;
    public String tokenType;
    public long expiresAt;
    public long expiresIn;

    private String authenticationCode;
    private String refreshCode;
}
