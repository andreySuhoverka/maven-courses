package epam.sukhoverka.exception.system.dao;


public class DBSystemException extends Exception {
    public DBSystemException(String message) {
        super(message);
    }

    public DBSystemException(String message, Exception e) {
        super(message, e);
    }
}
