package com.mruruc.fu.university.service.university.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String additionalInfo;
    private String mapsUrl;
    private String buildingName;
    private String campusName;
}
