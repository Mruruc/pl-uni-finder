package com.mruruc.fu.search.service.repository;


import com.mruruc.fu.search.service.dto.ProgramSearchRequest;
import com.mruruc.fu.search.service.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.typesense.api.Client;
import org.typesense.model.SearchResult;

import java.util.Optional;

import static com.mruruc.fu.search.service.util.ProgramSchema.PROGRAMS_COLLECTION;


@Repository
@RequiredArgsConstructor
@Slf4j
public class ProgramSearchRepository {
    private final Client typesenseClient;

    public Optional<SearchResult> search(ProgramSearchRequest request) {
        var searchParameters = SearchUtil.buildSearchParameters(request);
        try {
            var searchResult = typesenseClient.collections(PROGRAMS_COLLECTION)
                    .documents()
                    .search(searchParameters);

            return Optional.ofNullable(searchResult);
        } catch (Exception exception) {
            log.error("Error searching programs: {}", exception.getMessage());
            return Optional.empty();
        }
    }
}
