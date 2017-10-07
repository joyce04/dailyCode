package joyce.daily.rest;

import joyce.daily.validation.Binding;
import joyce.daily.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PlayerController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    Map<String, String> bindingErrors = new HashMap<>();
                    bindingErrors.put("code", error.getCode());
                    bindingErrors.put("field", ((FieldError) error).getField());
                    return bindingErrors;
                }).collect(Collectors.toList());

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/players")
    public Player registerPlayer(@Validated(Binding.Create.class) @RequestBody Player player) {
        System.out.println(player.toString() + " is registered");
        return player;
    }
}
