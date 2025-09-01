package com.mruruc.fu.search.service.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ErrorResponse(
        String message,
        HttpStatus statusCode,
        String timestamp,
        Map<String, String> validationErrors
) {
}
