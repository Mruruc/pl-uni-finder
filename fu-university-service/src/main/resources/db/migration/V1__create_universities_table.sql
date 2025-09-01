-- Universities table
CREATE TABLE universities
(
    id                     BIGINT GENERATED ALWAYS AS IDENTITY,
    name                   VARCHAR(255)                NOT NULL,
    description            TEXT,
    type                   VARCHAR(50)                 NOT NULL,
    established_year       INTEGER,
    world_ranking          INTEGER,
    national_ranking       INTEGER,
    website_url            VARCHAR(255),
    logo_url               VARCHAR(255),
    rating                 FLOAT                                DEFAULT 0.0,
    total_students         INTEGER,
    international_students INTEGER,
    is_active              BOOLEAN                     NOT NULL DEFAULT TRUE,
    -- Address fields (embedded)
    country                VARCHAR(255),
    city                   VARCHAR(255),
    street                 VARCHAR(255),
    postal_code            VARCHAR(255),
    additional_info        VARCHAR(255),
    maps_url               VARCHAR(255),
    building_name          VARCHAR(255),
    campus_name            VARCHAR(255),
    -- Audit fields
    created_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_universities
        PRIMARY KEY (id),

    CONSTRAINT uq_universities_name
        UNIQUE (name)
);