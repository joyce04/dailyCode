package joyce.daily.learning.exceptionHandler;

public class CustomException extends Exception {
    public CustomException(String message) {
        super("custom exception message");
    }
}
