package ru.nartemt.tone_engine_ver2.controller.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Hidden
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException e) {
        log.warn("Validation error : {}", e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().headers(buildHeaders()).body(errors);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleException(AuthenticationException e) {
        log.warn("Authentication error : {}", e.getMessage());
        HttpStatus status = HttpStatus.FORBIDDEN;
        ExceptionResponse response = new ExceptionResponse(status.value(), e.getMessage());
        return new ResponseEntity<>(response, buildHeaders(), status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleException(AccessDeniedException e) {
        log.warn("Access denied error : {}", e.getMessage());
        HttpStatus status = HttpStatus.FORBIDDEN;
        ExceptionResponse response = new ExceptionResponse(status.value(), e.getMessage());
        return new ResponseEntity<>(response, buildHeaders(), status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(EntityNotFoundException e) {
        log.warn("Entity not found : {}", e.getMessage());
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ExceptionResponse response = new ExceptionResponse(status.value(), e.getMessage());
        return new ResponseEntity<>(response, buildHeaders(), status);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionResponse> handleException(Throwable throwable) {
        log.error("Unexpected error occurred : ", throwable);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse(status.value(), throwable.getMessage());
        return new ResponseEntity<>(response, buildHeaders(), status);
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
