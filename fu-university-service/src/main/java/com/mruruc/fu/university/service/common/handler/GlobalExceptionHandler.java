package com.mruruc.fu.university.service.common.handler;


import com.mruruc.fu.university.service.common.exception.DtoNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DtoNotValidException.class)
    public ResponseEntity<ErrorDto> handleDtoNotValidException(DtoNotValidException exception) {
        var errorDto = ErrorDto.builder()
                .message("Invalid fields")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .validationErrors(exception.getViolations())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }


}
