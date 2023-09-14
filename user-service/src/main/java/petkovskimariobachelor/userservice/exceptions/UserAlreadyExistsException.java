package petkovskimariobachelor.userservice.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("User already exists exception");
    }
}
