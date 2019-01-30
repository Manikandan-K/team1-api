
drop TABLE USERS;
CREATE TABLE USERS (
	id    SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL,
	email varchar(600) NOT NULL,
	encodedPassword varchar(600) NULL
);

