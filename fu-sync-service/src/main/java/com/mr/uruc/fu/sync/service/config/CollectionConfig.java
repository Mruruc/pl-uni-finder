package com.mr.uruc.fu.sync.service.config;


import com.mr.uruc.fu.sync.service.typesene.CollectionService;
import com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.typesense.model.CollectionSchema;

@Component
@RequiredArgsConstructor
@Slf4j
public class CollectionConfig {
    private final CollectionService collectionService;

    @PostConstruct
    public void initCollections() {
        log.info("Initializing Typesense collections...");
        createProgramsCollection();
        log.info("Typesense collections initialized successfully.");
    }

    public void createProgramsCollection() {
        CollectionSchema programSchema = ProgramSchema.buildProgramSchema();
        collectionService.createCollectionIfNotExists(programSchema);
    }
}
