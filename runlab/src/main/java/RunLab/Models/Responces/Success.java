package RunLab.Models.Responces;

public class Success<T> extends CustomResponse<T> {

    public Success(){
        this.status = 200;
        this.message = "Success";
    }
}