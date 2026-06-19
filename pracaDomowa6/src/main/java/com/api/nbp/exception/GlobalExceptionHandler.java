package com.api.nbp.exception;

import com.api.nbp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NbpApiException.class)
    ResponseEntity<ErrorResponse> handleNbpApiException(NbpApiException exception) {
        HttpStatus status = HttpStatus.resolve(exception.getStatusCode());
        if (status == null) {
            status = HttpStatus.BAD_GATEWAY;
        }
        return buildResponse(status, exception.getMessage());
    }

    @ExceptionHandler(InvalidRequestException.class)
    ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class
    })
    ResponseEntity<ErrorResponse> handleBadRequest(Exception exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(new ErrorResponse(status.value(), status.getReasonPhrase(), message, LocalDateTime.now()));
    }
}
