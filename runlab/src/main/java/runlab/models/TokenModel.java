package runlab.models;

public class TokenModel {
    private String accessToken;
    private String refreshToken;

    TokenModel() {} 

    TokenModel(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken(){
        return this.accessToken;
    }

    public String getRefreshToken(){
        return this.refreshToken;
    }

    public void setAccessToken(String token){
        this.accessToken = token;
    }

    public void setRefreshToken(String token){
        this.refreshToken = token;
    }
}
