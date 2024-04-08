package kg.demo.dodo.exceptions;

import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandle {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandle(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<?> exceptionHandle(PasswordException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IncorrectEmailException.class)
    public ResponseEntity<?> exceptionHandle(IncorrectEmailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> exceptionHandle(JwtException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> exceptionHandle(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> exceptionHandle(RuntimeException e) {
        return new ResponseEntity<>( e.getMessage(), HttpStatus.CONFLICT);
    }


}
