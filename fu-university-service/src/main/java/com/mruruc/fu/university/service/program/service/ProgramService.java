package com.mruruc.fu.university.service.program.service;


import com.mruruc.fu.university.service.common.validation.DtoValidator;
import com.mruruc.fu.university.service.program.dto.ProgramCreateRequest;
import com.mruruc.fu.university.service.program.entity.Program;
import com.mruruc.fu.university.service.program.mapper.ProgramMapper;
import com.mruruc.fu.university.service.program.repository.ProgramRepository;
import com.mruruc.fu.university.service.sync.event.EventName;
import com.mruruc.fu.university.service.sync.event.SyncEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramService {
    private final DtoValidator<ProgramCreateRequest> dtoValidator;
    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;
    private final RequirementService requirementService;
    private final ApplicationEventPublisher eventPublisher;
    private final TagService tagService;

    @Transactional
    public Long saveProgram(ProgramCreateRequest request) {
        log.debug("Validating request: {}", request);
        dtoValidator.validate(request);

        // Map DTO -> Entity
        Program programEntity = programMapper.toEntity(request);

        // Requirements
        var requirements = requirementService.fetchRequirements(request.requirements());
        programEntity.setRequirements(requirements);

        // Tags
        var tags = tagService.resolveTags(request.existingTagIds(), request.newTags());
        programEntity.setTags(tags);

        // Save program
        Program savedProgram = programRepository.save(programEntity);
        log.debug("Program saved with ID: {}", savedProgram.getId());

        var program = programRepository.fetchProgramForSync(savedProgram.getId());


        eventPublisher.publishEvent(
                SyncEvent.<Program>builder()
                        .event(program)
                        .eventName(EventName.PROGRAM_CREATED)
                        .build()
        );

        return savedProgram.getId();
    }
}
