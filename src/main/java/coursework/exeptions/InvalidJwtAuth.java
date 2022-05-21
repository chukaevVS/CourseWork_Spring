package coursework.exeptions;

public class InvalidJwtAuth extends RuntimeException{
    public InvalidJwtAuth(String message) {
        super(message);
    }
}
