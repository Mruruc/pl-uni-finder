package com.mr.uruc.fu.sync.service.service;


import com.mr.uruc.fu.sync.service.exception.DocumentException;
import com.mr.uruc.fu.sync.service.typesene.TypesenseDocumentIndexer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.PROGRAMS_COLLECTION;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramDocumentIndexer {
    private final TypesenseDocumentIndexer documentService;


    public void indexPrograms(List<Map<String, Object>> programs) {
        if (programs == null || programs.isEmpty()) {
            log.info("No programs to sync, skipping.");
            return;
        }
        log.info("Starting to sync {} programs to collection '{}'.", programs.size(), PROGRAMS_COLLECTION);

        try {
            var result = documentService.bulkIndex(PROGRAMS_COLLECTION, programs);
            documentService.bulkIndex(PROGRAMS_COLLECTION, programs);
            log.info("Successfully synced {} of {} programs to collection '{}'.",
                    result.succeeded(), result.total(), PROGRAMS_COLLECTION);
        } catch (Exception exception) {
            log.error("Failed to sync programs to collection '{}', exception message: {}",
                    PROGRAMS_COLLECTION, exception.getMessage());
            throw new DocumentException("Failed to sync programs", exception);
        }
    }

}
