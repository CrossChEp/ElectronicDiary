CREATE TABLE homeworks(
    id BIGINT NOT NULL,
    subjectId BIGINT NOT NULL,
    classID BIGINT NOT NULL,
    content VARCHAR(25000) NOT NULL,
    CONSTRAINT subject FOREIGN KEY (subjectId) REFERENCES diary.working_schema.subjects(id),
    CONSTRAINT schoolClass FOREIGN KEY (classID) REFERENCES diary.working_schema.classes(id),
    PRIMARY KEY (id)
)