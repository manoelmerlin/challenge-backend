package com.backendchallenge.challenge.controller.ExceptionHandler;

import com.backendchallenge.challenge.services.exceptions.InvalidWithdraw;
import com.backendchallenge.challenge.services.exceptions.ObjectNotFoundException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception, HttpServletResponse request) {
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error",
                System.currentTimeMillis());
        error.addError("email", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletResponse request) {
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "Validation error", System.currentTimeMillis());
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    public ResponseEntity<StandardError> jdbcSQLIntegrityConstraintViolationException(
            JdbcSQLIntegrityConstraintViolationException exception, HttpServletResponse request) {
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "Validation error", System.currentTimeMillis());
        error.addError("email", "This e-mail is already registered");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidWithdraw.class)
    public ResponseEntity<StandardError> invalidWithdrawException(InvalidWithdraw exception, HttpServletResponse request) {
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "Validation error", System.currentTimeMillis());
        error.addError("withdrawValue", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
