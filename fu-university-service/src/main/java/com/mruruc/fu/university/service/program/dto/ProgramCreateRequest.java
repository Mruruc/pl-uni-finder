package com.mruruc.fu.university.service.program.dto;


import com.mruruc.fu.university.service.program.entity.ProgramLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record ProgramCreateRequest(
        @NotBlank(message = "Program name is required")
        String name,
        @NotBlank(message = "Program description is required")
        String description,
        @NotNull(message = "Program level is required")
        ProgramLevel level,
        @NotNull(message = "Program duration is required")
        double duration,
        @NotBlank(message = "Program language is required")
        String language,
        @NotNull(message = "Tuition fee is required")
        BigDecimal tuitionFee,
        int studentsCount,
        Instant nextDeadline,
        @NotNull(message = "University ID is required")
        Long universityId,
        Set<Long> existingTagIds,
        Set<String> newTags,
        @NotNull(message = "Requirements are required for the program")
        List<Long> requirements
) {
}
