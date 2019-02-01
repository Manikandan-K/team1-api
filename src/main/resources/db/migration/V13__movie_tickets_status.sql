CREATE TABLE IF NOT EXISTS movie_theatre (
   id SERIAL PRIMARY KEY,
   theatre_name varchar(255) NOT NULL,
   screen_name varchar(255) NOT NULL,
   tickets_count INT NOT NULL,
   tickets_sold INT NOT NULL,
   movie_showtime_id INT CONSTRAINT unq_showtime_id UNIQUE,
   FOREIGN KEY (movie_showtime_id) REFERENCES MOVIE_SHOWTIMES(id),
   UNIQUE (movie_showtime_id, id, screen_name, theatre_name)
 );