package joyce.daily.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {
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

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(getErrorMap(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(getErrorMap(e), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getErrorMap(Exception e) {
        Map<String, String> errorMap = new HashMap<>();

        if (e instanceof CustomException) {
            errorMap.put("code", "CUSTOM");
        } else {
            errorMap.put("code", "BASE");
        }
        errorMap.put("message", e.getLocalizedMessage());
        return errorMap;
    }
}
