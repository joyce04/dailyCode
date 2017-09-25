package joyce.daily.learning.exceptionHandler;

public class CustomException extends RuntimeException {
    public CustomException() {
        super("custom exception message");
    }
}
