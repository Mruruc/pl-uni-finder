package com.mr.uruc.fu.sync.service.typesene;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.typesense.api.Client;
import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectionService {
    private final Client typesenseClient;


    public CollectionResponse createCollection(CollectionSchema schema) {
        try {
            var response = typesenseClient.collections().create(schema);
            log.info("Collection created name='{}' fields='{}'", schema.getName(), schema.getFields());
            return response;
        } catch (Exception exception) {
            log.error("Collection creation failed name='{}' reason='{}'",
                    schema.getName(), exception.getMessage());
            throw new TypesenseException(exception.getMessage(), exception);
        }
    }

    public CollectionResponse createCollectionIfNotExists(CollectionSchema schema) {
        var collectionName = schema.getName();
        var existingCollection = this.getCollection(collectionName);
        if (existingCollection.isPresent()) {
            log.info("Collection already exists name='{}'", collectionName);
            return existingCollection.get();
        }
        return this.createCollection(schema);
    }

    public Optional<CollectionResponse> getCollection(String collectionName) {
        try {
            var collectionResponse = typesenseClient.collections(collectionName).retrieve();
            return Optional.of(collectionResponse);
        } catch (Exception exception) {
            log.error("Collection Not Found name='{}' reason={}",
                    collectionName, exception.getMessage());
            return Optional.empty();
        }
    }
}
