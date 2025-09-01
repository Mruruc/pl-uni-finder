package com.mruruc.fu.university.service.university.repository;


import com.mruruc.fu.university.service.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
