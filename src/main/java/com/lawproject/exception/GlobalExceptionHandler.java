package com.lawproject.exception;

import com.lawproject.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** Bean-validation failures (@Valid on @RequestBody) */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.putIfAbsent(fe.getField(), fe.getDefaultMessage());
        }

        String summary = fieldErrors.values().stream()
                .collect(Collectors.joining("; "));

        log.warn("Validation failed: {}", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(fieldErrors, 400, summary));
    }

    /** DB constraint violations (length, not-null, FK, etc.) */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrity(
            DataIntegrityViolationException ex) {

        log.error("Data integrity violation", ex);

        String root = ex.getMostSpecificCause().getMessage();
        String userMessage = "Invalid data submitted.";

        if (root != null) {
            String lower = root.toLowerCase();
            if (lower.contains("value too long")) {
                userMessage = "One of the fields is too long. Please shorten it and try again.";
            } else if (lower.contains("not-null") || lower.contains("null value")) {
                userMessage = "A required field is missing.";
            } else if (lower.contains("duplicate")) {
                userMessage = "A record with this value already exists.";
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, 400, userMessage));
    }

    /** Anything else we didn't anticipate */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
        log.error("Unhandled exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(null, 500, "Something went wrong. Please try again later."));
    }
}