package com.mruruc.fu.search.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        var fieldErrors = exception.getBindingResult().getFieldErrors();

        log.error("Validation failed: {}",
                fieldErrors.stream()
                        .map(fieldError -> fieldError.getField() + "=" + fieldError.getDefaultMessage())
                        .collect(Collectors.joining(", "))
        );

        var validationMap = fieldErrors.stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        LinkedHashMap::new,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.joining(", "))
                ));


        var errorResponse = ErrorResponse.builder()
                .message("Validation failed")
                .validationErrors(validationMap)
                .timestamp(LocalDateTime.now()
                        .truncatedTo(ChronoUnit.SECONDS)
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
