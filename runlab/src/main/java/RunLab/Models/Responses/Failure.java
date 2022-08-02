package RunLab.models.responses;

public class Failure<T> extends CustomResponse<T> {

    public Failure(){
        this.message = "General error occoured";
    }
}