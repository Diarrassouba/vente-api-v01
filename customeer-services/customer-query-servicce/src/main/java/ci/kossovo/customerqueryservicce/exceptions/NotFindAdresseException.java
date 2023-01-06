package ci.kossovo.customerqueryservicce.exceptions;

import java.util.NoSuchElementException;

public class NotFindAdresseException extends NoSuchElementException {

    public NotFindAdresseException(String message) {
        super(message);
    }
    
}
