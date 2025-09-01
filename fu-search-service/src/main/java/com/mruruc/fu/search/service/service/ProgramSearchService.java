package com.mruruc.fu.search.service.service;


import com.mruruc.fu.search.service.dto.ProgramSearchRequest;
import com.mruruc.fu.search.service.dto.ProgramSearchResponse;
import com.mruruc.fu.search.service.mapper.ProgramSearchMapper;
import com.mruruc.fu.search.service.repository.ProgramSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.typesense.model.SearchResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramSearchService {
    private final ProgramSearchRepository programSearchRepository;
    private final ProgramSearchMapper programSearchMapper;

    public ProgramSearchResponse searchPrograms(ProgramSearchRequest request) {
        log.debug("Searching programs with request: {}", request);
        Optional<SearchResult> searchResult = programSearchRepository.search(request);
        return programSearchMapper.toProgramSearchResponse(searchResult, request);
    }

    // for the debug purposes, this method is used to search programs without mapping
    public SearchResult searchProgramsV2(ProgramSearchRequest request) {
        log.debug("Searching programs with query: '{}' ,page: {}, perPage: {}",
                request.query(), request.page(), request.perPage());
        Optional<SearchResult> searchResult = programSearchRepository.search(request);
        return searchResult.orElse(new SearchResult());
    }

}
