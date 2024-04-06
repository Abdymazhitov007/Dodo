package kg.demo.dodo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> exceptionHandle(RuntimeException e) {
        return new ResponseEntity( e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandle(IllegalArgumentException e) {
        return new ResponseEntity( e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandle(MethodArgumentNotValidException e) {
        return new ResponseEntity( e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
