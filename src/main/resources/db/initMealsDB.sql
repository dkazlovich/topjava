DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS meals_seq;

CREATE SEQUENCE meals_seq START WITH 100000;

CREATE TABLE meals

(
    id          INTEGER PRIMARY KEY DEFAULT nextval('meals_seq'),
    userId      INTEGER,
    description VARCHAR   NOT NULL,
    dateTime    TIMESTAMP NOT NULL,
    calories    INTEGER   NOT NULL,
    UNIQUE (userId, dateTime),
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
);