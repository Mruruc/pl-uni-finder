package com.mruruc.fu.search.service.util;



import com.mruruc.fu.search.service.dto.ProgramSearchRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.typesense.model.SearchParameters;

import static com.mruruc.fu.search.service.util.ProgramSchema.NAME;
import static com.mruruc.fu.search.service.util.ProgramSchema.TAGS;
import static com.mruruc.fu.search.service.util.ProgramSchema.UNIVERSITY_NAME;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchUtil {
    public static final String DEFAULT_QUERY = "*";
    public static final String QUERY_BY_FIELDS = String.join(",", NAME, UNIVERSITY_NAME, TAGS);


    public static SearchParameters buildSearchParameters(ProgramSearchRequest request) {
        return new SearchParameters()
                .q(request.query().isBlank() ? DEFAULT_QUERY : request.query())
                .queryBy(QUERY_BY_FIELDS)
                .page(request.page())
                .perPage(request.perPage());
    }

}
