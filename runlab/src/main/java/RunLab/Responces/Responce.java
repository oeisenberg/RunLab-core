package RunLab.Responces;

public abstract class Responce{
    protected int status = 400;

    protected String message = "";

    public int getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }
}