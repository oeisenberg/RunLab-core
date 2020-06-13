package RunLab.Responces;

public class PullFailure extends Failure{

    public PullFailure(){
        this.status = 421; // Misdirected Request, server not able to produce a response
        this.message = "No new data to pull";
    }
}