package com.mruruc.fu.university.service.university.dto;


import com.mruruc.fu.university.service.university.entity.UniversityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UniversityCreateRequest(
        @NotBlank(message = "University name is required")
        String name,
        @NotBlank(message = "University description is required")
        String description,
        @NotNull(message = "University type is required")
        UniversityType type,
        @NotNull(message = "Established year is required")
        Integer establishedYear,
        Integer worldRanking,
        Integer nationalRanking,
        @NotBlank(message = "Website URL is required")
        String websiteUrl,
        @NotBlank(message = "Logo URL is required")
        String logoUrl,
        Integer totalStudents,
        Integer internationalStudents,
        @NotBlank(message = "Country is required")
        String country,
        @NotBlank(message = "City is required")
        String city,
        @NotBlank(message = "Street is required")
        String street,
        @NotBlank(message = "Postal code is required")
        String postalCode,
        String additionalInfo,
        @NotBlank(message = "Maps URL is required")
        String mapsUrl,
        @NotBlank(message = "Building name is required")
        String buildingName,
        @NotBlank(message = "Campus name is required")
        String campusName
) {
}
