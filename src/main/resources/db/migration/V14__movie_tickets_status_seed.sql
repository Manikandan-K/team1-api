INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('SATHYAM STUDIOS', 'STUDIO-5', 100,0);
INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('ESCAPE', 'CARVE', 50, 0);
INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('PALAZZO','SCREEN-5', 30, 0);
INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('ESCAPE', 'FRAME', 70, 0);
INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('ESCAPE', 'BLUSH', 80, 0);

UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 0) where id = 1;
UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 1) where id = 2;
UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 2) where id = 3;
UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 3) where id = 4;
UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 4) where id = 5;

INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('SATHYAM STUDIOS', 'STUDIO-5', 100,0);
INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('ESCAPE', 'CARVE', 50, 0);
INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('ESCAPE', 'FRAME', 70, 0);
INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('ESCAPE', 'BLUSH', 80, 0);
INSERT INTO movie_theatre(theatre_name, screen_name, tickets_count, tickets_sold) values ('PALAZZO','SCREEN-5', 30, 0);

UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 6) where id = 6;
UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 7) where id = 7;
UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 8) where id = 8;
UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 9) where id = 9;
UPDATE movie_theatre SET movie_showtime_id = (select id from movie_showtimes ORDER BY id  limit 1 offset 10) where id = 10;

