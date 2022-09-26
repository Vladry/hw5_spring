package vlad.homework5.exception;

public class StoringDataException extends RuntimeException {

    public StoringDataException(){}

    public StoringDataException(String msg){
        super( String.format("StoringDataException: %s", msg) );
    }
}
