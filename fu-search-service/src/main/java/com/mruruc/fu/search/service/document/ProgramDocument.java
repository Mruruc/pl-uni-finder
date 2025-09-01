package com.mruruc.fu.search.service.document;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ProgramDocument(
        String id,
        String name,
        String description,
        String level,
        String duration,
        String language,
        String tuition,
        double rating,
        int studentsCount,
        Instant nextDeadline,
        boolean isActive,
        List<String> tags,
        List<String> requirements,
        Long universityId,
        String universityName,
        String city) {
}
