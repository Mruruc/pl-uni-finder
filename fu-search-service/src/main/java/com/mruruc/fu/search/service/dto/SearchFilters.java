package com.mruruc.fu.search.service.dto;

public record SearchFilters(
        String level,
        String field,
        String city,
        String language,
        Integer tuitionMin,
        Integer tuitionMax
) {
}
