CREATE TABLE roles (
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    primary key (id)
);

CREATE TABLE subjects (
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    primary key (id)
);

CREATE TABLE timetables (
    id BIGINT NOT NULL,
    mondayId VARCHAR(255)[],
    tuesdayId VARCHAR(255)[],
    wednesdayId VARCHAR(255)[],
    thursdayId VARCHAR(255)[],
    fridayId VARCHAR(255)[],
    saturdayId VARCHAR(255)[],
    primary key (id)
);

CREATE TABLE classes (
    id BIGINT NOT NULL,
    number INT NOT NULL ,
    letter CHAR NOT NULL,
    timetableId BIGINT,
    CONSTRAINT timetable FOREIGN KEY (timetableId) REFERENCES timetables(id),
    primary key (id)
);

CREATE TABLE users (
    id BIGINT  NOT NULL,
    login VARCHAR(255) NOT NULL ,
    password VARCHAR(255) NOT NULL ,
    phone_number VARCHAR(16) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255),
    roleId BIGINT NOT NULL,
    classId BIGINT,
    CONSTRAINT userRole FOREIGN KEY (roleId) REFERENCES roles(id),
    CONSTRAINT userClass FOREIGN KEY (classId) REFERENCES classes(id),
    primary key (id)
);