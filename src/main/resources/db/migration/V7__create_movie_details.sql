CREATE TABLE MOVIE_DETAILS (
	id    SERIAL primary key,
	movie_id int NOT NULL,
	crew varchar(500) NULL,
	movieCast varchar(500) NULL,
	duration varchar(300) NULL,
	genere varchar(300) NULL,
	synopsis varchar(1000) NULL,
	language varchar(300) NULL,
	grade varchar(300) NULL,
	FOREIGN KEY (movie_id) REFERENCES MOVIE (id)

);