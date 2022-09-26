package vlad.homework5.exception;


public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(){}

    public DataNotFoundException(String msg){
        super(String.format("DataNotFoundException: %s", msg) );
    }
}
