package com.mruruc.fu.university.service.common.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorDto(
        String message,
        HttpStatus status,
        LocalDateTime timestamp,
        Map<String, String> validationErrors

) {
}
