-- Programs table
CREATE TABLE programs
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY,
    name           VARCHAR(255)   NOT NULL,
    description    TEXT           NOT NULL,
    level          VARCHAR(50)    NOT NULL,
    duration       FLOAT          NOT NULL,
    language       VARCHAR(100)   NOT NULL,
    tuition        DECIMAL(10, 2) NOT NULL,
    rating         FLOAT                   DEFAULT 0.0,
    students_count INTEGER                 DEFAULT 0,
    next_deadline  TIMESTAMP WITH TIME ZONE,
    is_active      BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    university_id  BIGINT         NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (university_id)
        REFERENCES universities (id)
        ON DELETE CASCADE

);

-- Table for tags
CREATE TABLE tags
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL UNIQUE,

    CONSTRAINT pk_tags
        PRIMARY KEY (id),
    CONSTRAINT uq_tags_name
        UNIQUE (name)
);


-- Program tags table
CREATE TABLE program_tags
(
    program_id BIGINT NOT NULL,
    tag_id     BIGINT NOT NULL,

    PRIMARY KEY (program_id, tag_id),
    CONSTRAINT fk_program
        FOREIGN KEY (program_id)
            REFERENCES programs (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_tag
        FOREIGN KEY (tag_id)
            REFERENCES tags (id)
            ON DELETE CASCADE
);

-- Requirements table
CREATE TABLE requirements
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,
    description TEXT NOT NULL,
    type        VARCHAR(50),

    CONSTRAINT pk_requirements
        PRIMARY KEY (id)
);

-- Program requirements table

CREATE TABLE program_requirements
(
    program_id     BIGINT NOT NULL,
    requirement_id BIGINT NOT NULL,

    PRIMARY KEY (program_id, requirement_id),

    CONSTRAINT fk_program_requirements_program
        FOREIGN KEY (program_id)
            REFERENCES programs (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_program_requirements_requirement
        FOREIGN KEY (requirement_id)
            REFERENCES requirements (id)
            ON DELETE CASCADE
);


CREATE INDEX IF NOT EXISTS idx_program_requirements_requirement
    ON program_requirements (requirement_id);

