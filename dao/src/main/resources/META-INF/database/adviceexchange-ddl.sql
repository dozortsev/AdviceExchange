/* Create Database */

DROP DATABASE IF EXISTS adviceexchange;

CREATE DATABASE adviceexchange
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE adviceexchange;


/* Table of User */

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (

  user_id         INT         NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  user_name       VARCHAR(50) NOT NULL,
  user_age        INT,
  user_about_me   TEXT,
  user_joined     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_location   VARCHAR(120),
  user_site       VARCHAR(120),
  user_email      VARCHAR(120) UNIQUE,
  user_password   VARCHAR(15) UNIQUE,
  user_reputation INT         NOT NULL DEFAULT 1
)
  ENGINE =InnoDB;


/* Table of Badge */

DROP TABLE IF EXISTS badge;
CREATE TABLE IF NOT EXISTS badge (

  bdg_id   INT                   NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  bdg_name ENUM('Admin', 'User') NOT NULL,
  bdg_desc TEXT(100)             NOT NULL
)
  ENGINE =InnoDB;


/* Table reference for User and Badges (many to many) */

DROP TABLE IF EXISTS user_badge;
CREATE TABLE IF NOT EXISTS user_badge (

  ub_badge_id INT NOT NULL,
  ub_user_id  INT NOT NULL
)
  ENGINE =InnoDB;


/* Table of Comment */

DROP TABLE IF EXISTS comment;
CREATE TABLE IF NOT EXISTS comment (

  cm_id          INT UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,
  cm_user_id     INT        NOT NULL,
  cm_question_id INT        NOT NULL,
  cm_content     TEXT(1000) NOT NULL,
  cm_created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
  ENGINE =InnoDB;


/* Table of Answer */

DROP TABLE IF EXISTS answer;
CREATE TABLE IF NOT EXISTS answer (

  asw_id          INT           NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  asw_user_id     INT           NOT NULL,
  asw_question_id INT           NOT NULL,
  asw_votes       INT DEFAULT 0 NOT NULL,
  asw_content     TEXT          NOT NULL,
  asw_created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  asw_accepted    BOOLEAN       NOT NULL DEFAULT FALSE
)
  ENGINE =InnoDB;


/* Table of Question */

DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question (

  qs_id      INT          NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  qs_user_id INT          NOT NULL,
  qs_name    VARCHAR(200) NOT NULL,
  qs_votes   INT          NOT NULL,
  qs_content TEXT         NOT NULL,
  qs_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
  ENGINE =InnoDB;


/* Table of Tag */

DROP TABLE IF EXISTS tag;
CREATE TABLE IF NOT EXISTS tag (

  tag_id   INT         NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  tag_name VARCHAR(20) NOT NULL,
  tag_desc TEXT        NOT NULL
)
  ENGINE =InnoDB;


/* Table reference with Question and Tags (many to many) */

DROP TABLE IF EXISTS question_tag;
CREATE TABLE IF NOT EXISTS question_tag (

  qt_question_id INT NOT NULL,
  qt_tag_id      INT NOT NULL
)
  ENGINE =InnoDB;


/* Define references */

ALTER TABLE user_badge
ADD CONSTRAINT fk_ub_user_id FOREIGN KEY (ub_user_id) REFERENCES user (user_id),
ADD CONSTRAINT fk_ub_badge_id FOREIGN KEY (ub_badge_id) REFERENCES badge (bdg_id);


ALTER TABLE question_tag
ADD CONSTRAINT fk_qt_tag_id FOREIGN KEY (qt_tag_id) REFERENCES tag (tag_id),
ADD CONSTRAINT fk_qt_question_id FOREIGN KEY (qt_question_id) REFERENCES question (qs_id);


ALTER TABLE answer
ADD CONSTRAINT fk_aws_user_id FOREIGN KEY (asw_user_id) REFERENCES user (user_id)
  ON DELETE CASCADE,
ADD CONSTRAINT fk_aws_question_id FOREIGN KEY (asw_question_id) REFERENCES question (qs_id)
  ON DELETE CASCADE;


ALTER TABLE question
ADD CONSTRAINT fk_qs_user_id FOREIGN KEY (qs_user_id) REFERENCES user (user_id)
  ON DELETE CASCADE;


ALTER TABLE comment
ADD CONSTRAINT fk_cm_user_id FOREIGN KEY (cm_user_id) REFERENCES user (user_id)
  ON DELETE CASCADE,
ADD CONSTRAINT fk_cm_question_id FOREIGN KEY (cm_question_id) REFERENCES question (qs_id)
  ON DELETE CASCADE;