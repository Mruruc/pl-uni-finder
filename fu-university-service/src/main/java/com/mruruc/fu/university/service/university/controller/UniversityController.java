package com.mruruc.fu.university.service.university.controller;

import com.mruruc.fu.university.service.common.util.WebUtil;
import com.mruruc.fu.university.service.university.dto.UniversityCreateRequest;
import com.mruruc.fu.university.service.university.dto.UniversityDto;
import com.mruruc.fu.university.service.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/universities")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @GetMapping
    public List<UniversityDto> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    @PostMapping
    public ResponseEntity<Void> createUniversity(@RequestBody(required = true) UniversityCreateRequest request) {
        Long universityId = universityService.saveUniversity(request);
        URI uri = WebUtil.buildUriForResource(universityId);
        return ResponseEntity.created(uri).build();
    }
}
