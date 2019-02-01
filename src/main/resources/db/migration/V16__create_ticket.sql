CREATE TABLE IF NOT EXISTS movie_ticket (
   id SERIAL PRIMARY KEY,
   theatre_id INT NOT NULL,
   movie_showtime_id INT NOT NULL,
   booking_id VARCHAR (150)  NOT NULL,
   user_name varchar(300) NOT NULL,
   user_email varchar(600) NOT NULL,
   ticket_count INT NOT NULL,
   FOREIGN KEY (movie_showtime_id) REFERENCES MOVIE_SHOWTIMES(id),
   FOREIGN key (theatre_id) REFERENCES movie_theatre(id)
 );