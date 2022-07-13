package RunLab.Models.Responces;

public class Failure<T> extends CustomResponse<T> {

    public Failure(){
        this.message = "General error occoured";
    }
}