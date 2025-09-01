package com.mruruc.fu.university.service.program.mapper;

import com.mruruc.fu.university.service.program.dto.ProgramCreateRequest;
import com.mruruc.fu.university.service.program.entity.Program;
import com.mruruc.fu.university.service.program.entity.Requirement;
import com.mruruc.fu.university.service.university.entity.University;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProgramMapper {

    public Program toEntity(ProgramCreateRequest program) {
        var university = University.builder()
                .id(program.universityId())
                .build();

        return Program.builder()
                .name(program.name())
                .description(program.description())
                .level(program.level())
                .duration(program.duration())
                .language(program.language())
                .tuition(program.tuitionFee())
                .rating(0.0)
                .studentsCount(program.studentsCount())
                .nextDeadline(program.nextDeadline())
                .isActive(true)
                .university(university)
                .build();
    }
}
