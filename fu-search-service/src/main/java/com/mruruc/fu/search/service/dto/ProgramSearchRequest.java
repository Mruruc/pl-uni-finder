package com.mruruc.fu.search.service.dto;


import jakarta.validation.constraints.NotBlank;

public record ProgramSearchRequest(
        @NotBlank(message = "Query must not be blank")
        String query,
        Integer page,
        Integer perPage
) {
    public Integer page() {
        return (page != null && page > 0) ? page : 1;
    }

    public Integer perPage() {
        return (perPage != null && perPage > 0) ? perPage : 10;
    }
}
