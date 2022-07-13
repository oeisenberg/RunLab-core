package RunLab.Models.Exceptions;

public class InvalidMapboxProfile extends Exception {

    // Serialisable class requires an id 
    private static final long serialVersionUID = 1L;

    public InvalidMapboxProfile(String errorMsg) {
        super(errorMsg);
    }
}