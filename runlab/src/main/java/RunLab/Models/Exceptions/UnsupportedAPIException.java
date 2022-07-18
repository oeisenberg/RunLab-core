package RunLab.models.exceptions;

public class UnsupportedAPIException extends Exception {
    // Serialisable class requires an id 
    private static final long serialVersionUID = 1L;

    public UnsupportedAPIException(String errorMsg) {
        super(errorMsg);
    }
}