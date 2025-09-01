package com.mruruc.fu.university.service.sync.mapper;

import com.mruruc.fu.university.service.program.entity.Program;
import com.mruruc.fu.university.service.program.entity.Requirement;
import com.mruruc.fu.university.service.program.entity.Tag;
import com.mruruc.fu.university.service.sync.event.EventName;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.CITY;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.DESCRIPTION;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.DURATION;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.ID;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.IS_ACTIVE;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.LANGUAGE;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.LEVEL;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.NAME;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.NEXT_DEADLINE;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.PROGRAMS_COLLECTION;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.RATING;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.REQUIREMENTS;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.STUDENTS_COUNT;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.TAGS;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.TUITION;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.UNIVERSITY_ID;
import static com.mruruc.fu.university.service.sync.typesense.ProgramSchema.UNIVERSITY_NAME;

@Component
public class ProgramDocumentMapper implements TypesenseDocumentMapper<Program> {

    @Override
    public Map<String, Object> mapToDocument(Program program) {

        var programDoc = new HashMap<String, Object>();
        programDoc.put(ID, String.valueOf(program.getId()));
        programDoc.put(NAME, program.getName());
        programDoc.put(DESCRIPTION, program.getDescription());
        programDoc.put(LEVEL, program.getLevel().name());
        programDoc.put(DURATION, program.getDuration());
        programDoc.put(LANGUAGE, program.getLanguage());
        programDoc.put(TUITION, program.getTuition());
        programDoc.put(RATING, program.getRating());
        programDoc.put(STUDENTS_COUNT, program.getStudentsCount());
        programDoc.put(NEXT_DEADLINE, program.getNextDeadline().toString());
        programDoc.put(IS_ACTIVE, program.isActive());

        programDoc.put(TAGS, program.getTags() != null
                ? program.getTags().stream().map(Tag::getName).toList()
                : List.of());

        programDoc.put(REQUIREMENTS, program.getRequirements() != null
                ? program.getRequirements().stream().map(Requirement::getDescription).toList()
                : List.of());

        programDoc.put(UNIVERSITY_ID, program.getUniversity().getId());
        programDoc.put(UNIVERSITY_NAME, program.getUniversity().getName());
        programDoc.put(CITY, program.getUniversity().getAddress().getCity());
        return programDoc;
    }

    @Override
    public boolean supports(EventName eventName) {
        return eventName.equals(EventName.PROGRAM_CREATED);
    }

    @Override
    public String getCollectionName() {
        return PROGRAMS_COLLECTION;
    }
}
