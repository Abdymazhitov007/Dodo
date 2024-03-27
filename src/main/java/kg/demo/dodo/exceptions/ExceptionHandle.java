package kg.demo.dodo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> exceptionHandle(RuntimeException e) {
        return new ResponseEntity( e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
