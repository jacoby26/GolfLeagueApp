BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
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

CREATE TABLE Public."courses" (course_id serial, course_name varchar(100) NOT NULL, address varchar(100) NOT NULL,
							   city varchar(100) NOT NULL, course_state varchar (100) NOT NULL, zip_code int NOT NULL, latitude numeric NOT NULL,
							   longitude numeric NOT NULL);
COPY Public."courses" FROM 'C:\Users\Student\AppData\Local\Temp\withLocation.csv' DELIMITER ',' CSV;

CREATE TABLE users_leagues (
	user_id int, 
	league_id int,
	CONSTRAINT PK_users_leagues PRIMARY KEY (user_id, league_id),
	CONSTRAINT FK_users_leagues_user FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_users_leagues_league FOREIGN KEY (league_id) REFERENCES leagues (league_id)
);

CREATE TABLE leagues (
	league_id serial NOT NULL,
	league_name varchar (50) NOT NULL,
	course_id int NOT NULL,
	CONSTRAINT PK_leagues PRIMARY KEY (league_id),
	CONSTRAINT FK_leagues_course FOREIGN KEY (course_id) REFERENCES courses (course_id)
);


COMMIT TRANSACTION;
