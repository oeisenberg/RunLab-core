package RunLab.Models.Responses;

public abstract class CustomResponse<T> {
    protected int status = 400;
    protected String header;
    protected String message;
    protected T body;

    public int getStatus(){
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

}