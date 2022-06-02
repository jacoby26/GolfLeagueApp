BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS leagues;
DROP TABLE IF EXISTS users_leagues;
DROP TABLE IF EXISTS tee_times;
DROP TABLE IF EXISTS rounds;
DROP SEQUENCE IF EXISTS seq_user_id;

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

CREATE TABLE Public."courses" (course_id serial, 
	course_name varchar(100) NOT NULL, 
	address varchar(100) NOT NULL,
	city varchar(100) NOT NULL, 
	course_state varchar (100) NOT NULL, 
	zip_code int NOT NULL, 
	latitude numeric NOT NULL,
	longitude numeric NOT NULL,
	CONSTRAINT PK_courses PRIMARY KEY (course_id)
	);

COPY Public."courses" FROM 'C:\Users\Student\AppData\Local\Temp\withLocationNoId.csv' DELIMITER ',' CSV;

CREATE TABLE leagues (
	league_id serial NOT NULL,
	league_name varchar (50) NOT NULL,
	course_id int NOT NULL,
	CONSTRAINT PK_leagues PRIMARY KEY (league_id),
	CONSTRAINT FK_leagues_course FOREIGN KEY (course_id) REFERENCES courses (course_id),
	CONSTRAINT UQ_league_name UNIQUE (league_name)
);

CREATE TABLE users_leagues (
	user_id int, 
	league_id int,
	CONSTRAINT PK_users_leagues PRIMARY KEY (user_id, league_id),
	CONSTRAINT FK_users_leagues_user FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_users_leagues_league FOREIGN KEY (league_id) REFERENCES leagues (league_id)
);

CREATE TABLE tee_times (
	tee_time_id serial NOT NULL,
	tee_time_date date NOT NULL,
	tee_time time NOT NULL,
	course_id int NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_tee_time PRIMARY KEY (tee_time_id),
	CONSTRAINT FK_tee_time_course FOREIGN KEY (course_id) REFERENCES courses (course_id),
	CONSTRAINT FK_tee_time_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE rounds (
	round_id serial NOT NULL,
	score int NOT NULL,
	tee_time_id int NOT NULL,
	CONSTRAINT PK_round PRIMARY KEY (round_id),
	CONSTRAINT FK_round_tee_time FOREIGN KEY (tee_time_id) REFERENCES tee_times (tee_time_id),
	CONSTRAINT CHK_score CHECK (score > 0 AND score < 150)
);


COMMIT TRANSACTION;
