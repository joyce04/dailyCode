package joyce.daily.exceptionHandler;

public class CustomException extends RuntimeException {
    public CustomException() {
        super("custom exception message");
    }
}
