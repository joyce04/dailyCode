package joyce.daily.learning.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {
    /**
     * September 20 2017
     * <p>
     * different levels of exception handler in spring boot
     * 1) within method by try & catch
     * 2) exceptionHandler = controller level
     * 3) controller advice = global
     * <p>
     * practice global level of exception handling
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
    public String handleCustomException(CustomException e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public String handleException(Exception e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }
}
