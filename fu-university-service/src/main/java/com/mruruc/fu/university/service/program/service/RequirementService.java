package com.mruruc.fu.university.service.program.service;

import com.mruruc.fu.university.service.program.entity.Requirement;
import com.mruruc.fu.university.service.program.repository.RequirementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RequirementService {
    private final RequirementsRepository requirementsRepository;

    @Transactional(readOnly = true)
    public Set<Requirement> fetchRequirements(List<Long> requirementIds) {
        var requirements = requirementsRepository.findAllById(requirementIds);
        return Set.copyOf(requirements);
    }
}
