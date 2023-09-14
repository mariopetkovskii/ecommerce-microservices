package petkovskimariobachelor.userservice.exceptions;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException() {
        super("Password do not match exception");
    }
}
