package com.mruruc.fu.university.service.university.dto;


import com.mruruc.fu.university.service.university.entity.UniversityType;
import lombok.Builder;

@Builder
public record UniversityDto(
        Long id,
        String name,
        String description,
        UniversityType type,
        Integer establishedYear,
        Integer worldRanking,
        Integer nationalRanking,
        String websiteUrl,
        String logoUrl,
        double rating,
        Integer totalStudents,
        Integer internationalStudents,
        String country,
        String city,
        String street,
        String postalCode,
        String additionalInfo,
        String mapsUrl,
        String buildingName,
        String campusName
) {
}
