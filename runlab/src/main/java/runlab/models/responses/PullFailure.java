package runlab.models.responses;

public class PullFailure<T> extends Failure<T> {

    public PullFailure(){
        this.status = 421; // Misdirected Request, server not able to produce a response
        this.message = "No new data to pull";
    }
}
