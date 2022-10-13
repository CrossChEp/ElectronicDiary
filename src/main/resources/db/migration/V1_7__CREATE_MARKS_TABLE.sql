create TABLE marks(
  id BIGINT NOT NULL,
  mark INT NOT NULL ,
  weight INT NOT NULL ,
  subjectId BIGINT NOT NULL ,
  userId BIGINT NOT NULL ,
  comment VARCHAR(255),
  date DATE NOT NULL ,
  CONSTRAINT subject FOREIGN KEY (subjectId) REFERENCES diary.working_schema.subjects(id),
  CONSTRAINT student FOREIGN KEY (userId) REFERENCES diary.working_schema.users(id),
  PRIMARY KEY (id)
);