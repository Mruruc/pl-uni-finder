package com.mruruc.fu.university.service.program.repository;


import com.mruruc.fu.university.service.program.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query(
            """
                    SELECT p, u
                    FROM Program p
                    JOIN FETCH p.university u
                    LEFT JOIN FETCH u.address
                    WHERE p.id = :programId
                    """
    )
    Program fetchProgramForSync(@Param("programId") Long id);

}
