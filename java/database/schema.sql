BEGIN TRANSACTION;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS leagues CASCADE;
DROP TABLE IF EXISTS users_leagues CASCADE;
DROP TABLE IF EXISTS tee_times CASCADE;
DROP TABLE IF EXISTS tee_times_users CASCADE;
DROP TABLE IF EXISTS rounds CASCADE;
DROP TABLE IF EXISTS invites CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id CASCADE;
DROP SEQUENCE IF EXISTS seq_course_id CASCADE;


-- ROLLBACK;
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE TABLE users (
    user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
    username varchar(50) NOT NULL,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('alex','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('nate','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('jacob','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('dillon','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('niko','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('jordan','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('jack','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('arnold','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('tiger','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('phil','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('rickie','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('rory','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('dustin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');


CREATE SEQUENCE seq_course_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE Public."courses" (course_id int DEFAULT nextval('seq_course_id'::regclass) NOT NULL,
    course_name varchar(100) NOT NULL,
    address varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    course_state varchar (100) NOT NULL,
    zip_code int NOT NULL,
    latitude numeric NOT NULL,
    longitude numeric NOT NULL,
    CONSTRAINT PK_courses PRIMARY KEY (course_id)
    );
-- COPY Public."courses" FROM 'C:\Users\Student\AppData\Local\Temp\withLocation.csv' DELIMITER ',' CSV;

CREATE TABLE leagues (
    league_id serial NOT NULL,
    league_name varchar (50) NOT NULL,
    course_id int NOT NULL,
    league_organizer int NOT NULL,
    CONSTRAINT PK_leagues PRIMARY KEY (league_id),
    CONSTRAINT FK_leagues_course FOREIGN KEY (course_id) REFERENCES courses (course_id),
    CONSTRAINT FK_league_organizer FOREIGN KEY (league_organizer) REFERENCES users (user_id),
    CONSTRAINT UQ_league_name UNIQUE (league_name)
);

CREATE TABLE users_leagues (
    user_id int,
    league_id int,
    CONSTRAINT PK_users_leagues PRIMARY KEY (user_id, league_id),
    CONSTRAINT FK_users_leagues_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_users_leagues_league FOREIGN KEY (league_id) REFERENCES leagues (league_id)
);



CREATE TABLE rounds (
    round_id serial NOT NULL,
    tee_time varchar (50) NOT NULL,
    round_date varchar (50) NOT NULL,
    league_id int NOT NULL,
    CONSTRAINT PK_round PRIMARY KEY (round_id),
    CONSTRAINT FK_league_id FOREIGN KEY (league_id) REFERENCES leagues (league_id)
);


CREATE TABLE scores (
    score_id serial NOT NULL,
    score int NOT NULL,
    user_id int NOT NULL,
    round_id int NOT NULL,
      CONSTRAINT PK_score PRIMARY KEY (score_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_round FOREIGN KEY (round_id) REFERENCES rounds (round_id),
    CONSTRAINT CHK_score CHECK (score > 0 AND score < 150)
);


CREATE Table invites (
    invite_id serial NOT NULL,
    league_id integer NOT NULL,
    user_id integer NOT NULL,
    is_accepted boolean,
    CONSTRAINT primekey_invites PRIMARY KEY (invite_id),
    CONSTRAINT fk_league FOREIGN KEY (league_id) REFERENCES public.leagues(league_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(user_id)
);


COMMIT TRANSACTION;
-- ROLLBACK;