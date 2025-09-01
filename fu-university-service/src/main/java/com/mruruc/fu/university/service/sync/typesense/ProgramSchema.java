package com.mruruc.fu.university.service.sync.typesense;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProgramSchema {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LEVEL = "level";
    public static final String DURATION = "duration";
    public static final String LANGUAGE = "language";
    public static final String TUITION = "tuition";
    public static final String RATING = "rating";
    public static final String STUDENTS_COUNT = "studentsCount";
    public static final String NEXT_DEADLINE = "nextDeadline";
    public static final String IS_ACTIVE = "isActive";
    public static final String TAGS = "tags";
    public static final String REQUIREMENTS = "requirements";
    public static final String UNIVERSITY_ID = "universityId";
    public static final String UNIVERSITY_NAME = "university_name";
    public static final String CITY = "city";

    public static final String PROGRAMS_COLLECTION = "programs";
}
