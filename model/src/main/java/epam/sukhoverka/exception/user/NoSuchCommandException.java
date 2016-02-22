package epam.sukhoverka.exception.user;


public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String message) {
        super(message);
    }

    public NoSuchCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
