package avlyakulov.timur.userservice.exception;

public class UserAgeNotValidException extends RuntimeException {
    public UserAgeNotValidException(String message) {
        super(message);
    }
}