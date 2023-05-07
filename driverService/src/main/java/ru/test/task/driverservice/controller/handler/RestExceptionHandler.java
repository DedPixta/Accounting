package ru.test.task.driverservice.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.test.task.driverservice.exception.NotEnoughMoney;
import ru.test.task.driverservice.exception.NotFoundException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    private record ExceptionResponse(List<String> errors) {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public ExceptionResponse notFound(final NotFoundException ex) {
        log.error("Not found exception: {}", ex.getMessage());
        return new ExceptionResponse(List.of(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ExceptionResponse illegalArgument(final IllegalArgumentException ex) {
        log.error("Illegal argument exception: {}", ex.getMessage());
        return new ExceptionResponse(List.of(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Validation exception: {}", ex.getMessage());
        return new ExceptionResponse(List.of(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughMoney.class)
    public ExceptionResponse handleValidationExceptions(NotEnoughMoney ex) {
        log.error("Account balance exception: {}", ex.getMessage());
        return new ExceptionResponse(List.of(ex.getMessage()));
    }
}
