package com.mruruc.fu.search.service.dto;



import com.mruruc.fu.search.service.document.ProgramDocument;
import lombok.Builder;

import java.util.List;

@Builder
public record ProgramSearchResponse(
        List<ProgramDocument> programs,
        int totalFound,
        int page,
        int perPage
) {
}
