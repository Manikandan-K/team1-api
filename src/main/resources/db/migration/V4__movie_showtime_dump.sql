INSERT INTO movie_showtimes (movie_id, movie_date, movie_time)
SELECT id, 	current_date + integer '1', time '11:00' + interval '0.5h' * id from movie
;

INSERT INTO movie_showtimes (movie_id, movie_date, movie_time)
SELECT id, 	current_date + integer '2', time '11:00' + interval '0.5h' * id from movie
;

INSERT INTO movie_showtimes (movie_id, movie_date, movie_time)
SELECT id, 	current_date + integer '3', time '11:00' + interval '0.5h' * id from movie
;

INSERT INTO movie_showtimes (movie_id, movie_date, movie_time)
SELECT id, 	current_date + integer '4', time '11:00' + interval '0.5h' * id from movie
;

INSERT INTO movie_showtimes (movie_id, movie_date, movie_time)
SELECT id, 	current_date + integer '5', time '11:00' + interval '0.5h' * id from movie
;

INSERT INTO movie_showtimes (movie_id, movie_date, movie_time)
SELECT id, 	current_date + integer '6', time '11:00' + interval '0.5h' * id from movie
;