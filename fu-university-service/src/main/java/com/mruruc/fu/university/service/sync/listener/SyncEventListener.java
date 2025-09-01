package com.mruruc.fu.university.service.sync.listener;


import com.mruruc.fu.university.service.sync.event.SyncEvent;
import com.mruruc.fu.university.service.sync.mapper.DocumentMapperFactory;
import com.mruruc.fu.university.service.sync.mapper.TypesenseDocumentMapper;
import com.mruruc.fu.university.service.sync.typesense.DocumentIndexer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SyncEventListener {

    private final DocumentIndexer documentIndexer;
    private final DocumentMapperFactory mapperFactory;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSyncEvent(SyncEvent<?> syncEvent) {
        log.debug("Received SyncEvent: {}", syncEvent);
        TypesenseDocumentMapper mapper = mapperFactory.getMapper(syncEvent.getEventName())
                .orElseThrow(() -> new IllegalArgumentException("No mapper found for event: " + syncEvent.getEventName()));

        String collectionName = mapper.getCollectionName();

        Map<String, Object> document = mapper.mapToDocument(syncEvent.getEvent());

        documentIndexer.indexDocument(collectionName, document);
    }
}
