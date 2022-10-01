CREATE TABLE schools(
  id BIGINT NOT NULL,
  number BIGINT NOT NULL ,
  primary key (id)
);

ALTER TABLE diary.working_schema.users ADD schoolId BIGINT;
ALTER TABLE diary.working_schema.users ADD CONSTRAINT school FOREIGN KEY (schoolId)
    REFERENCES schools(id);


ALTER TABLE diary.working_schema.classes ADD schoolID BIGINT;
ALTER TABLE diary.working_schema.classes ADD CONSTRAINT school FOREIGN KEY (schoolID)
    REFERENCES schools(id);
