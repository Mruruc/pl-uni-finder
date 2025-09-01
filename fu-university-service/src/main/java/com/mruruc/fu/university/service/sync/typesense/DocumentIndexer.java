package com.mruruc.fu.university.service.sync.typesense;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.typesense.api.Client;
import org.typesense.model.DirtyValues;
import org.typesense.model.ImportDocumentsParameters;
import org.typesense.model.IndexAction;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentIndexer {
    private final Client typesenseClient;

    public void indexDocument(String collectionName, Map<String, Object> document) {
        log.info("Indexing document into collection '{}'", collectionName);
        try {
            var params = buildImportParams(IndexAction.CREATE);
            var response = typesenseClient.collections(collectionName)
                    .documents()
                    .create(document, params);
            log.info("Document indexed successfully: {}", response);
        } catch (Exception exception) {
            log.error("Failed to index document into collection '{}': {}", collectionName, exception.getMessage());
        }
    }

    private ImportDocumentsParameters buildImportParams(IndexAction action) {
        var params = new ImportDocumentsParameters();
        params.dirtyValues(DirtyValues.REJECT);
        params.action(action);
        params.returnId(true);
        // params.returnDoc(true);
        return params;
    }
}
