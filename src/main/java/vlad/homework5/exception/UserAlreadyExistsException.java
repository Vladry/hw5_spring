package vlad.homework5.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(){}

    public UserAlreadyExistsException(String msg){
        super( String.format("user %s already exists" , msg) );
    }
}
