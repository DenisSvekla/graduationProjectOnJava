package com.tms.ExceprtionResolver;

import java.time.LocalDateTime;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
public class ExceptionControllerAdvice {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionDetails> ValidationExceptionHandler() {
        ExceptionDetails details = new ExceptionDetails(HttpStatus.CONFLICT, LocalDateTime.now(), "we have invalid data");
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDetails> dontHaveDataForObject(Exception e){
        ExceptionDetails details = new ExceptionDetails(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(),"" +e.getClass());
        return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpStatus> usernameNotFound(Exception e) {
        log.warn("UsernameNotFoundException: " + e.getMessage());
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFoundExceptionHandler(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDetails(HttpStatus.NOT_FOUND, LocalDateTime.now(), exception.getMessage())
        );
    }

    @ExceptionHandler(OtherException.class)
    public ResponseEntity<ExceptionDetails> otherException(OtherException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDetails(HttpStatus.NOT_FOUND, LocalDateTime.now(), exception.getMessage())
        );
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ExceptionDetails> sqlException(PSQLException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDetails(HttpStatus.CONFLICT, LocalDateTime.now(), exception.getServerErrorMessage().getMessage())
        );
    }
}
