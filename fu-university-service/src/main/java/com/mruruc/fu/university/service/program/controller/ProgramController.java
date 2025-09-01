package com.mruruc.fu.university.service.program.controller;


import com.mruruc.fu.university.service.common.util.WebUtil;
import com.mruruc.fu.university.service.program.dto.ProgramCreateRequest;
import com.mruruc.fu.university.service.program.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<Void> createProgram(@RequestBody ProgramCreateRequest program) {
        Long programId = programService.saveProgram(program);
        URI location = WebUtil.buildUriForResource(programId);
        return ResponseEntity.created(location).build();
    }
}
