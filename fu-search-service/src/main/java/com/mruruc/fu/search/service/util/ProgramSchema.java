package com.mruruc.fu.search.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.typesense.api.FieldTypes;
import org.typesense.model.CollectionSchema;
import org.typesense.model.Field;

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


    public static CollectionSchema buildProgramSchema() {
        return new CollectionSchema().name(PROGRAMS_COLLECTION)
                .addFieldsItem(new Field().name(ID).type(FieldTypes.INT64))
                .addFieldsItem(new Field().name(NAME).type(FieldTypes.STRING).index(true))
                .addFieldsItem(new Field().name(DESCRIPTION).type(FieldTypes.STRING).index(false))
                .addFieldsItem(new Field().name(LEVEL).type(FieldTypes.STRING).index(true))
                .addFieldsItem(new Field().name(DURATION).type(FieldTypes.FLOAT).index(false))
                .addFieldsItem(new Field().name(LANGUAGE).type(FieldTypes.STRING).index(true))
                .addFieldsItem(new Field().name(TUITION).type(FieldTypes.FLOAT).index(true))
                .addFieldsItem(new Field().name(RATING).type(FieldTypes.FLOAT).index(true))
                .addFieldsItem(new Field().name(STUDENTS_COUNT).type(FieldTypes.INT32).index(false))
                .addFieldsItem(new Field().name(NEXT_DEADLINE).type(FieldTypes.STRING).index(true))
                .addFieldsItem(new Field().name(IS_ACTIVE).type(FieldTypes.BOOL).index(false))
                .addFieldsItem(new Field().name(TAGS).type(FieldTypes.STRING_ARRAY).index(true))
                .addFieldsItem(new Field().name(REQUIREMENTS).type(FieldTypes.STRING_ARRAY).index(false))
                .addFieldsItem(new Field().name(UNIVERSITY_ID).type(FieldTypes.INT64).index(true))
                .addFieldsItem(new Field().name(UNIVERSITY_NAME).type(FieldTypes.STRING).index(true))
                .addFieldsItem(new Field().name(CITY).type(FieldTypes.STRING).index(true));
    }

}
