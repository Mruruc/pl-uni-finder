package com.mruruc.fu.search.service.mapper;



import com.mruruc.fu.search.service.document.ProgramDocument;
import com.mruruc.fu.search.service.dto.ProgramSearchRequest;
import com.mruruc.fu.search.service.dto.ProgramSearchResponse;
import com.mruruc.fu.search.service.util.ProgramSchema;
import org.springframework.stereotype.Component;
import org.typesense.model.SearchResult;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class ProgramSearchMapper {
    public ProgramSearchResponse toProgramSearchResponse(
            Optional<SearchResult> searchResult, ProgramSearchRequest request) {
        if (searchResult.isEmpty()) {
            return ProgramSearchResponse.builder()
                    .programs(List.of())
                    .totalFound(0)
                    .page(request.page())
                    .perPage(request.perPage())
                    .build();
        }

        SearchResult result = searchResult.get();

        Integer totalDocumentFound = result.getFound();
        Integer page = result.getPage();
        Integer perPage = result.getRequestParams().getPerPage();

        var programs = result.getHits().stream()
                .map(searchResultHit -> mapToProgramDocument.apply(searchResultHit.getDocument()))
                .toList();

        return ProgramSearchResponse.builder()
                .programs(programs)
                .totalFound(totalDocumentFound)
                .page(page)
                .perPage(perPage)
                .build();

    }

    Function<Map<String, Object>, ProgramDocument> mapToProgramDocument = document -> {
        String id = document.get(ProgramSchema.ID).toString();
        String name = document.get(ProgramSchema.NAME).toString();
        String university = document.get(ProgramSchema.UNIVERSITY_NAME).toString();
        String city = document.get(ProgramSchema.CITY).toString();
        String level = document.get(ProgramSchema.LEVEL).toString();
        String duration = document.get(ProgramSchema.DURATION).toString();
        Long universityId = Long.parseLong(document.get(ProgramSchema.UNIVERSITY_ID).toString());
        String language = document.get(ProgramSchema.LANGUAGE).toString();
        String tuition = document.get(ProgramSchema.TUITION).toString();
        double rating = Double.parseDouble(document.get(ProgramSchema.RATING).toString());
        int studentsCount = Integer.parseInt(document.get(ProgramSchema.STUDENTS_COUNT).toString());
        String nextDeadline = document.get(ProgramSchema.NEXT_DEADLINE).toString();
        String description = document.get(ProgramSchema.DESCRIPTION) != null ?
                document.get(ProgramSchema.DESCRIPTION).toString() : null;
        List<String> requirements = document.get(ProgramSchema.REQUIREMENTS) != null ?
                (List<String>) document.get(ProgramSchema.REQUIREMENTS) : List.of();
        List<String> tags = document.get(ProgramSchema.TAGS) != null ?
                (List<String>) document.get(ProgramSchema.TAGS) : List.of();
        Boolean isActive = document.get(ProgramSchema.IS_ACTIVE) != null ?
                Boolean.parseBoolean(document.get(ProgramSchema.IS_ACTIVE).toString()) : null;

        return ProgramDocument.builder()
                .id(id)
                .name(name)
                .universityName(university)
                .city(city)
                .level(level)
                .duration(duration)
                .universityId(universityId)
                .language(language)
                .tuition(tuition)
                .rating(rating)
                .studentsCount(studentsCount)
                .nextDeadline(Instant.parse(nextDeadline))
                .description(description)
                .requirements(requirements)
                .tags(tags)
                .isActive(isActive)
                .build();
    };
}
