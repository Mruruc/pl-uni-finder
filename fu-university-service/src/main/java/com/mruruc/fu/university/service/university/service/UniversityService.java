package com.mruruc.fu.university.service.university.service;


import com.mruruc.fu.university.service.common.validation.DtoValidator;
import com.mruruc.fu.university.service.university.dto.UniversityCreateRequest;
import com.mruruc.fu.university.service.university.dto.UniversityDto;
import com.mruruc.fu.university.service.university.entity.University;
import com.mruruc.fu.university.service.university.mapper.UniversityMapper;
import com.mruruc.fu.university.service.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;
    private final DtoValidator<UniversityCreateRequest> dtoValidator;

    public Long saveUniversity(UniversityCreateRequest request) {
        dtoValidator.validate(request);
        University university = UniversityMapper.fromCreateRequest(request);
        University savedUniversity = universityRepository.save(university);
        return savedUniversity.getId();
    }

    public List<UniversityDto> getAllUniversities() {
        return universityRepository.findAll()
                .stream()
                .map(UniversityMapper::toUniversityDto)
                .toList();
    }
}
