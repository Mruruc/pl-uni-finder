package com.mruruc.fu.search.service.controller;


import com.mruruc.fu.search.service.dto.ProgramSearchRequest;
import com.mruruc.fu.search.service.dto.ProgramSearchResponse;
import com.mruruc.fu.search.service.service.ProgramSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.typesense.model.SearchResult;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
@Slf4j
public class ProgramSearchController {
    private final ProgramSearchService programSearchService;

    // Endpoint to search programs with raw SearchResult, its just for debugging purposes
    @GetMapping("/raw")
    public ResponseEntity<SearchResult> searchProgramsRaw(
            @ModelAttribute ProgramSearchRequest request) {
        SearchResult searchResult = programSearchService.searchProgramsV2(request);
        return ResponseEntity.ok(searchResult);
    }

    @GetMapping
    public ResponseEntity<ProgramSearchResponse> searchPrograms(
            @Valid @ModelAttribute ProgramSearchRequest request) {
        log.info("Received request: {}", request);
        ProgramSearchResponse response = programSearchService.searchPrograms(request);
        return ResponseEntity.ok(response);
    }

}
