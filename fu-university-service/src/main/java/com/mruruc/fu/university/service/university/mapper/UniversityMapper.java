package com.mruruc.fu.university.service.university.mapper;


import com.mruruc.fu.university.service.university.dto.UniversityCreateRequest;
import com.mruruc.fu.university.service.university.dto.UniversityDto;
import com.mruruc.fu.university.service.university.entity.Address;
import com.mruruc.fu.university.service.university.entity.University;

public class UniversityMapper {
    private UniversityMapper() {
    }

    public static University fromCreateRequest(UniversityCreateRequest request) {
        String country = request.country() != null ? request.country() : "Poland";
        var address = Address.builder()
                .country(country)
                .city(request.city())
                .street(request.street())
                .postalCode(request.postalCode())
                .additionalInfo(request.additionalInfo())
                .mapsUrl(request.mapsUrl())
                .buildingName(request.buildingName())
                .campusName(request.campusName())
                .build();

        return University.builder()
                .name(request.name())
                .description(request.description())
                .type(request.type())
                .establishedYear(request.establishedYear())
                .worldRanking(request.worldRanking() != null ? request.worldRanking() : 0)
                .nationalRanking(request.nationalRanking() != null ? request.nationalRanking() : 0)
                .websiteUrl(request.websiteUrl())
                .logoUrl(request.logoUrl())
                .rating(0.0)
                .totalStudents(request.totalStudents())
                .internationalStudents(request.internationalStudents())
                .address(address)
                .build();
    }

    public static UniversityDto toUniversityDto(University university) {
        var address = university.getAddress();

        return UniversityDto.builder()
                .id(university.getId())
                .name(university.getName())
                .description(university.getDescription())
                .type(university.getType())
                .establishedYear(university.getEstablishedYear())
                .worldRanking(university.getWorldRanking())
                .nationalRanking(university.getNationalRanking())
                .websiteUrl(university.getWebsiteUrl())
                .logoUrl(university.getLogoUrl())
                .rating(university.getRating())
                .totalStudents(university.getTotalStudents())
                .internationalStudents(university.getInternationalStudents())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .additionalInfo(address.getAdditionalInfo())
                .mapsUrl(address.getMapsUrl())
                .buildingName(address.getBuildingName())
                .campusName(address.getCampusName())
                .build();
    }
}
