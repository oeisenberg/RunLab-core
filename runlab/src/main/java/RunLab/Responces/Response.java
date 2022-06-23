package RunLab.Responces;

import java.net.http.HttpResponse;

public abstract class Response { // implements HttpResponse ?
    protected int status = 400;

    protected String message;
    protected String body;

    public int getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

    public void ingest(HttpResponse<String> otherResponse){
        // TODO: copy header values
        setBody(otherResponse.body().replace("\"", "'"));
    }

    private void setBody(String body){
        this.message = body;
    }

}