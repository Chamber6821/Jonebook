package com.example.jonebook;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorLogger {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Message handleInternalServerError(RuntimeException ex) {
        log.error("Unexpected exception", ex);
        return new Message("Oops...");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ HttpClientErrorException.class, HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class })
    public Message handleBadRequestException(Exception ex) {
        log.error("Bad request", ex);
        return new Message("Something wrong");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Message handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("Type mismatch", ex);
        return new Message(String.format("Invalid format of parameter: %s", ex.getName()));
    }

    public record Message(String message) {
    }
}
