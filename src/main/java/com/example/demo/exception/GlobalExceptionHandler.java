package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>>
    handleValidationException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        Map<String, Object> response = buildResponse(HttpStatus.BAD_REQUEST, "Bad request", "Validation failed");
        response.put("details", errors);
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler({HttpMessageNotReadableException.class,MethodArgumentTypeMismatchException.class})


    public ResponseEntity<Map<String, Object>> handleBadRequestException(Exception exception){
        Map<String, Object> response = buildResponse(HttpStatus.BAD_REQUEST,"Yêu cầu không hợp lệ", "Dữ liệu 0 hop lệ");
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(WatchNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleWatchNotFoundException(WatchNotFoundException exception){
        Map<String, Object> response = buildResponse(HttpStatus.NOT_FOUND,"Not found", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})


    public ResponseEntity<Map<String, Object>> handleNotFoundException(Exception exception){
        Map<String, Object> response = buildResponse(HttpStatus.NOT_FOUND, "Not found","Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);


    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exception){
        Map<String, Object> response = buildResponse(HttpStatus.METHOD_NOT_ALLOWED,"Method not allowed",exception.getMessage());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);

    }

    private Map<String, Object> buildResponse(HttpStatus status, String error, String message){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        response.put("message", message);
        return response;
    }
}
