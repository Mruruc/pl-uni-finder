package com.mr.uruc.fu.sync.service.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.CITY;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.DESCRIPTION;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.DURATION;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.ID;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.IS_ACTIVE;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.LANGUAGE;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.LEVEL;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.NAME;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.NEXT_DEADLINE;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.RATING;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.REQUIREMENTS;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.STUDENTS_COUNT;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.TAGS;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.TUITION;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.UNIVERSITY_ID;
import static com.mr.uruc.fu.sync.service.typesene.schema.ProgramSchema.UNIVERSITY_NAME;


@Repository
@RequiredArgsConstructor
@Slf4j
public class ProgramRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public static final String SQL_QUERY = """
            SELECT
                p.id, p.name, p.description, p.level, p.duration, p.language,
                p.tuition, p.rating, p.students_count, p.next_deadline, p.is_active,
                u.id AS university_id, u.name  AS university_name, u.city,
                (SELECT STRING_AGG(DISTINCT t.name, '|') FROM program_tags pt
                    JOIN tags t ON t.id = pt.tag_id WHERE pt.program_id = p.id) AS tags,
                (SELECT STRING_AGG(rq.description, '|') FROM requirements rq
                    WHERE rq.program_id = p.id) AS requirements
            FROM programs p
            LEFT JOIN universities u ON u.id = p.university_id
            ORDER BY p.id
            LIMIT :limit 
            OFFSET :offset;
            """;

    public static final String COUNT_QUERY = "SELECT COUNT(id) FROM programs";


    public long count() {
        log.info("Executing count query for programs.");
        return jdbcTemplate.queryForObject(COUNT_QUERY, Map.of(), Long.class);
    }

    public List<Map<String, Object>> findProgramsForSync(int limit, long offset) {
        log.info("Fetching programs for sync with limit: {} and offset: {}", limit, offset);
        var params = new MapSqlParameterSource()
                .addValue("limit", limit)
                .addValue("offset", offset);

        return jdbcTemplate.query(SQL_QUERY, params, (rs, rowNum) -> mapToProgramDocument(rs));
    }


    private Map<String, Object> mapToProgramDocument(ResultSet rs) throws SQLException {

        String tagsString = rs.getString("tags");
        Set<String> tags = ((tagsString != null) && !tagsString.isEmpty())
                ? Set.of(tagsString.split("\\|"))
                : Set.of();

        String requirementsString = rs.getString("requirements");
        Set<String> requirements = (requirementsString != null && !requirementsString.isEmpty())
                ? Set.of(requirementsString.split("\\|"))
                : Set.of();

        var map = new HashMap<String, Object>();
        map.put(ID, rs.getString("id"));
        map.put(NAME, rs.getString("name"));
        map.put(DESCRIPTION, rs.getString("description"));
        map.put(LEVEL, rs.getString("level"));
        map.put(DURATION, rs.getFloat("duration"));
        map.put(LANGUAGE, rs.getString("language"));
        map.put(TUITION, rs.getBigDecimal("tuition"));
        map.put(RATING, rs.getDouble("rating"));
        map.put(STUDENTS_COUNT, rs.getInt("students_count"));
        map.put(NEXT_DEADLINE, rs.getTimestamp("next_deadline").toInstant().toString());
        map.put(IS_ACTIVE, rs.getBoolean("is_active"));
        map.put(TAGS, tags);
        map.put(REQUIREMENTS, requirements);
        map.put(UNIVERSITY_ID, rs.getLong("university_id"));
        map.put(UNIVERSITY_NAME, rs.getString("university_name"));
        map.put(CITY, rs.getString("city"));
        return map;
    }
}