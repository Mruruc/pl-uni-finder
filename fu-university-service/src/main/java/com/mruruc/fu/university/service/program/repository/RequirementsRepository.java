package com.mruruc.fu.university.service.program.repository;

import com.mruruc.fu.university.service.program.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementsRepository extends JpaRepository<Requirement, Long> {
}
