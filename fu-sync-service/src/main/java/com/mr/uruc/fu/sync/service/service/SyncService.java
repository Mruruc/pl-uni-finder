package com.mr.uruc.fu.sync.service.service;


import com.mr.uruc.fu.sync.service.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SyncService {
    public static final int BATCH_SIZE = 500;
    private final ProgramRepository programRepository;
    private final ProgramDocumentIndexer documentIndexer;


    @Async
    @EventListener(ApplicationReadyEvent.class)
    public void initSearchIndex() {
        log.info("Starting initial search index synchronization...");
        syncPrograms();
    }


    private void syncPrograms() {
        final long totalPrograms = programRepository.count();
        if (totalPrograms == 0) {
            log.warn("No programs found in the database to process.");
            return;
        }

        log.info("Processing {} total programs in batches of {}.", totalPrograms, BATCH_SIZE);
        int page = 0;
        while (true) {
            long offset = (long) page * BATCH_SIZE;
            var programs = programRepository.findProgramsForSync(BATCH_SIZE, offset);

            if (programs.isEmpty()) {
                break;
            }

            log.debug("Processing page {} with {} programs.", page + 1, programs.size());
            documentIndexer.indexPrograms(programs);
            page++;
        }
    }
}