DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
  userId   VARCHAR(12) NOT NULL,
  password VARCHAR(12) NOT NULL,
  name     VARCHAR(20) NOT NULL,
  email    VARCHAR(50),

  PRIMARY KEY (userId)
);

INSERT INTO USERS VALUES ('admin', 'password', 'admin', 'admin@slipp.net');

DROP TABLE IF EXISTS QUESTIONS;

CREATE TABLE QUESTIONS (
  questionId    BIGINT AUTO_INCREMENT,
  writer        VARCHAR(30)   NOT NULL,
  title         VARCHAR(50)   NOT NULL,
  contents      VARCHAR(5000) NOT NULL,
  createdDate   TIMESTAMP     NOT NULL,
  countOfAnswer INT,
  PRIMARY KEY (questionId)
);

DROP TABLE IF EXISTS ANSWERS;

CREATE TABLE ANSWERS (
  answerId    BIGINT AUTO_INCREMENT,
  writer      VARCHAR(30)   NOT NULL,
  contents    VARCHAR(5000) NOT NULL,
  createdDate TIMESTAMP     NOT NULL,
  questionId  BIGINT        NOT NULL,
  PRIMARY KEY (answerId)
);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
  (1, 'admin',
   'Hello World!',
   'Hi there',
   CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
  (2, 'bw',
   'Awesome',
   'Awesome',
   CURRENT_TIMESTAMP(), 0);

INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
  (3, 'sy',
   'iron man',
   'avengers',
   CURRENT_TIMESTAMP(), 0);


INSERT INTO QUESTIONS (questionId, writer, title, contents, createdDate, countOfAnswer) VALUES
  (4, 'mj',
   'beauty and the beast',
   'pretty woman working down the street ~',
   CURRENT_TIMESTAMP(), 0);
