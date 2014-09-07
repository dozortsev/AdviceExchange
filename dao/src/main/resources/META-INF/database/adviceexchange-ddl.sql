
-- Create Database

DROP DATABASE IF EXISTS adviceexchange;

CREATE DATABASE adviceexchange
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE adviceexchange;


-- Table of User

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (

  id         INT         NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(50) NOT NULL,
  age        INT,
  about_me   TEXT,
  joined     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  location   VARCHAR(120),
  site       VARCHAR(70),
  email      VARCHAR(50) NOT NULL UNIQUE,
  password   VARCHAR(32) NOT NULL,
  enabled    BOOLEAN     NOT NULL DEFAULT TRUE,
  reputation INT         NOT NULL DEFAULT 1
) ENGINE = InnoDB;


-- Table of Badge

DROP TABLE IF EXISTS badge;
CREATE TABLE IF NOT EXISTS badge (

  id     INT         NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  name   VARCHAR(30) NOT NULL UNIQUE,
  `desc` TEXT(100)   NOT NULL
) ENGINE = InnoDB;


-- Table reference for User and Badges (many to many)

DROP TABLE IF EXISTS user_badge;
CREATE TABLE IF NOT EXISTS user_badge (

  badge_id INT NOT NULL,
  user_id  INT NOT NULL
) ENGINE = InnoDB;


-- Table of UserActivity

DROP TABLE IF EXISTS user_activity;
CREATE TABLE IF NOT EXISTS user_activity (

  id      INT                                    NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  type    ENUM ('ANSWER', 'QUESTION', 'COMMENT') NOT NULL,
  user_id INT                                    NOT NULL,
  content TEXT                                   NOT NULL,
  active  BOOLEAN                                NOT NULL DEFAULT TRUE,
  created TIMESTAMP                              NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;


-- Table of Comment

DROP TABLE IF EXISTS comment;
CREATE TABLE IF NOT EXISTS comment (

  id    INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  qt_id INT NOT NULL
) ENGINE = InnoDB;


-- Table of Answer

DROP TABLE IF EXISTS answer;
CREATE TABLE IF NOT EXISTS answer (

  id       INT     NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  qt_id    INT     NOT NULL,
  accepted BOOLEAN NOT NULL DEFAULT FALSE
) ENGINE = InnoDB;


-- Table of Question

DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question (

  id        INT          NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  title     VARCHAR(200) NOT NULL,
  asw_count INT          NOT NULL DEFAULT 0
) ENGINE = InnoDB;


-- Table of Tag

DROP TABLE IF EXISTS tag;
CREATE TABLE IF NOT EXISTS tag (

  id     INT         NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  name   VARCHAR(20) NOT NULL UNIQUE,
  `desc` TEXT        NOT NULL
) ENGINE = InnoDB;


-- Table reference with Question and Tags (many to many)

DROP TABLE IF EXISTS question_tag;
CREATE TABLE IF NOT EXISTS question_tag (

  qt_id  INT NOT NULL,
  tag_id INT NOT NULL
) ENGINE = InnoDB;


-- Table of Votes

DROP TABLE IF EXISTS vote;
CREATE TABLE vote (

  id          INT       NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  user_id     INT       NOT NULL,
  activity_id INT       NOT NULL,
  score       INT       NOT NULL,
  created     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;


-- Define references

ALTER TABLE user_activity
ADD CONSTRAINT fk_ua_user_id FOREIGN KEY (user_id) REFERENCES user (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;


ALTER TABLE comment
ADD CONSTRAINT fk_cm_id FOREIGN KEY (id) REFERENCES user_activity (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
ADD CONSTRAINT fk_cm_question_id FOREIGN KEY (qt_id) REFERENCES question (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;


ALTER TABLE answer
ADD CONSTRAINT fk_asw_id FOREIGN KEY (id) REFERENCES user_activity (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
ADD CONSTRAINT fk_asw_qs_id FOREIGN KEY (qt_id) REFERENCES question (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;


ALTER TABLE question_tag
ADD CONSTRAINT fk_qt_tag_id FOREIGN KEY (tag_id) REFERENCES tag (id),
ADD CONSTRAINT fk_qt_question_id FOREIGN KEY (qt_id) REFERENCES question (id);


ALTER TABLE question
ADD CONSTRAINT fk_qs_id FOREIGN KEY (id) REFERENCES user_activity (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;


ALTER TABLE user_badge
ADD CONSTRAINT fk_ub_user_id FOREIGN KEY (user_id) REFERENCES user (id),
ADD CONSTRAINT fk_ub_badge_id FOREIGN KEY (badge_id) REFERENCES badge (id);


ALTER TABLE vote
ADD CONSTRAINT fk_vt_user_id FOREIGN KEY (user_id) REFERENCES user (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
ADD CONSTRAINT fk_vt_activity_id FOREIGN KEY (activity_id) REFERENCES user_activity (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;