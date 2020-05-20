package exceptions;

/**
 * This is an exception thrown when a map is not set and it is accessed
 */
public class MapNotSetException extends Exception {
    public MapNotSetException(String message) {
        super(message);
    }
}
