package com.mruruc.fu.university.service.common.validation;


import com.mruruc.fu.university.service.common.exception.DtoNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DtoValidator<T> {
    private final Validator validator;

    public void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) throw new DtoNotValidException(violations);
    }
}
