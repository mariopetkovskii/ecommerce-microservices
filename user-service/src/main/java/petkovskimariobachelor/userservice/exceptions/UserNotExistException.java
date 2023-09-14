package petkovskimariobachelor.userservice.exceptions;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("User not exists");
    }
}
