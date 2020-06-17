package RunLab.Models;

public class codeModel {
    private String code;

    codeModel() {} 

    public codeModel(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setCode(String token){
        this.code = token;
    }
}