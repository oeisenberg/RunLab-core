package RunLab.Models;

public class KeysModel {
    private Integer client_id;
    private String client_secret;
    private String code;
    private String access_token;
    private String refresh_token;

    KeysModel() {} 

    public KeysModel(Integer client_id, String client_secret, String code, String accessToken, String refreshToken){
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.access_token = accessToken;
        this.refresh_token = refreshToken;
    }

    public Integer getClientId(){
        return this.client_id;
    }

    public String getClientSecret(){
        return this.client_secret;
    }

    public String getAccessCode(){
        return this.code;
    }

    public String getAccessToken(){
        return this.access_token;
    }

    public String getRefreshToken(){
        return this.refresh_token;
    }

    public void setClientId(Integer id){
        this.client_id = id;
    }

    public void setClientSecret(String token){
        this.client_secret = token;
    }

    public void setCode(String token){
        this.code = token;
    }

    public void setAccessToken(String token){
        this.access_token = token;
    }

    public void setRefreshToken(String token){
        this.refresh_token = token;
    }
}