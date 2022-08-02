package RunLab.models.exceptions;

public class InvalidRequest extends Exception {
    // Serialisable class requires an id 
    private static final long serialVersionUID = 1L;

    public InvalidRequest(String errorMsg) {
        super(errorMsg);
    }
}