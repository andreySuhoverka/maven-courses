package epam.sukhoverka.exception.system;


import epam.sukhoverka.exception.system.dao.DBSystemException;

public class NoSuchEntityException extends DBSystemException {

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Exception cause) {
        super(message, cause);
    }

}
