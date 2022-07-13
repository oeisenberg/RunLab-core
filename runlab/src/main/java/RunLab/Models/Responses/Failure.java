package RunLab.Models.Responses;

public class Failure<T> extends CustomResponse<T> {

    public Failure(){
        this.message = "General error occoured";
    }
}