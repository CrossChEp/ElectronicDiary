ALTER TABLE diary.working_schema.timetables RENAME COLUMN mondayid TO monday;
ALTER TABLE diary.working_schema.timetables RENAME COLUMN tuesdayid TO tuesday;
ALTER TABLE diary.working_schema.timetables RENAME COLUMN wednesdayid TO wednesday;
ALTER TABLE diary.working_schema.timetables RENAME COLUMN thursdayid TO thursday;
ALTER TABLE diary.working_schema.timetables RENAME COLUMN fridayid TO friday;
ALTER TABLE diary.working_schema.timetables RENAME COLUMN saturdayid TO saturday;
ALTER TABLE diary.working_schema.timetables ADD COLUMN sunday VARCHAR(255)[];