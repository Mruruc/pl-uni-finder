package com.mr.uruc.fu.sync.service.typesene;

import com.mr.uruc.fu.sync.service.exception.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.typesense.api.Client;
import org.typesense.model.DirtyValues;
import org.typesense.model.ImportDocumentsParameters;
import org.typesense.model.IndexAction;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypesenseDocumentIndexer {
    private static final int DEFAULT_MAX_BATCH_SIZE = 100;

    private final Client typesenseClient;

    public BulkIndexResult bulkIndex(String collectionName, List<Map<String, Object>> documents) {
        if (documents == null || documents.isEmpty()) {
            log.info("No documents to index for collection '{}'", collectionName);
            return new BulkIndexResult(0, 0, 0);
        }

        var bulkIndexResult = performBulkIndex(collectionName, documents);

        log.info("Bulk import finished for collection='{}': total={} succeeded={} failed={}",
                collectionName, bulkIndexResult.total(),
                bulkIndexResult.succeeded(), bulkIndexResult.failed());

        return bulkIndexResult;
    }

    private BulkIndexResult performBulkIndex(String collectionName, List<Map<String, Object>> documents) {
        int totalDocs = documents.size();
        int succeeded = 0;
        int failed = 0;

        var params = buildImportParams(IndexAction.CREATE);

        for (int i = 0; i < totalDocs; i += DEFAULT_MAX_BATCH_SIZE) {
            int end = Math.min(i + DEFAULT_MAX_BATCH_SIZE, totalDocs);
            List<Map<String, Object>> batch = documents.subList(i, end);

            log.info("Bulk importing {} documents into collection='{}' action='{}' ({}-{})",
                    batch.size(), collectionName, params.getAction(), i + 1, end);

            String rawResults = sendBatchToTypesense(collectionName, batch, params);

            var batchResult = parseBatchResult(rawResults);
            succeeded += batchResult.succeeded();
            failed += batchResult.failed();

        }

        return new BulkIndexResult(totalDocs, succeeded, failed);
    }

    private String sendBatchToTypesense(String collectionName,
                                        List<Map<String, Object>> batch,
                                        ImportDocumentsParameters params) {
        try {
            log.info("Bulk importing {} docs into collection={} action={}",
                    batch.size(), collectionName, params.getAction());

            return typesenseClient.collections(collectionName)
                    .documents()
                    .import_(batch, params);
        } catch (Exception e) {
            log.error("Batch import failed collection={} reason={}", collectionName, e.getMessage(), e);
            throw new DocumentException("Batch import failed for collection=" + collectionName, e);
        }
    }

    private BatchResult parseBatchResult(String rawResults) {
        int succeeded = 0;
        int failed = 0;

        for (String line : rawResults.split("\n")) {
            if (line.contains("\"success\":true")) {
                succeeded++;
            } else {
                failed++;
                log.warn("Failed document import result: {}", line);
            }
        }

        return new BatchResult(succeeded, failed);
    }

    private ImportDocumentsParameters buildImportParams(IndexAction action) {
        var params = new ImportDocumentsParameters();
        params.dirtyValues(DirtyValues.REJECT);
        params.action(action);
        params.returnId(true);
        // params.returnDoc(true);
        return params;
    }


    public record BulkIndexResult(int total, int succeeded, int failed) {
    }

    private record BatchResult(int succeeded, int failed) {
    }
}
