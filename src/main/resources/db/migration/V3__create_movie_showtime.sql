CREATE TABLE IF NOT EXISTS movie_showtimes (
  id SERIAL PRIMARY KEY,
  movie_id INT NOT NULL,
  movie_date DATE,
  movie_time TIME,
  FOREIGN KEY (movie_id) REFERENCES MOVIE(id),
  UNIQUE (movie_id, movie_date, movie_time)
);