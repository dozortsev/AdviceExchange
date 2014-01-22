
/* Create Database */

DROP DATABASE IF EXISTS adviceexchange;

CREATE DATABASE adviceexchange
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE adviceexchange;



  /* Create Tables */

  /* Table with Users */

DROP TABLE IF EXISTS ad_user;
CREATE TABLE IF NOT EXISTS ad_user (

  user_id INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(50) NOT NULL,
  user_age DATE DEFAULT NULL,
  user_about_me TEXT,
  user_joined TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_location VARCHAR(120),
  user_site VARCHAR(120),
  user_email VARCHAR(120) UNIQUE,
  user_password VARCHAR(15) UNIQUE,
  user_reputation INT NOT NULL DEFAULT 1
) engine=InnoDB;


  /* Table with Comments */

DROP TABLE IF EXISTS ad_comment;
CREATE TABLE IF NOT EXISTS ad_comment (

  cm_id INT UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,
  cm_content TEXT NOT NULL,
  cm_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) engine=InnoDB;


DROP TABLE IF EXISTS ad_user_comment;
CREATE TABLE IF NOT EXISTS ad_user_comment (

  uc_id INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  uc_user_id INT NOT NULL,
  uc_comment_id INT NOT NULL
) engine=InnoDB;


  /* Table with Answers */

DROP TABLE IF EXISTS ad_answer;
CREATE TABLE IF NOT EXISTS ad_answer (

  asw_id INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
  asw_votes INT DEFAULT 1 NOT NULL,
  asw_content TEXT,
  asw_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  asw_accepted BOOLEAN DEFAULT FALSE NOT NULL
) engine=InnoDB;



  /* Define references */

ALTER TABLE ad_user_comment
    ADD CONSTRAINT fk_user_comment_user_id FOREIGN KEY (uc_user_id) REFERENCES ad_user(user_id),
    ADD CONSTRAINT fk_user_comment_comment_id FOREIGN KEY (uc_comment_id) REFERENCES ad_comment(cm_id);
