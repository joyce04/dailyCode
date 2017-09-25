package joyce.daily.learning.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/test")
public class TestController {

    @ResponseStatus(OK)
    @GetMapping(value = {"/custom-exception/{id}", "/custom-exception/"})
    public ResponseEntity testCustomExceptionHandle(@PathVariable(required = false) String test) {
        System.out.println("custom exception test:::" + test);
        return Optional.ofNullable(test)
                .map((found) -> new ResponseEntity<>(found, OK))
                .orElseThrow(CustomException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"/exception", "/"})
    public ResponseEntity testExceptionHandle(@RequestBody(required = false) Object object) {
        return Optional.ofNullable(object)
                .map(found -> new ResponseEntity<>(found, CREATED))
                .orElseThrow(() -> new RuntimeException("run time exception"));
    }
}
