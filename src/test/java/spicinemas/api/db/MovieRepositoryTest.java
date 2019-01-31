package spicinemas.api.db;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.jooq.DSLContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import spicinemas.SpiCinemasApplication;
import spicinemas.api.model.Movie;
import spicinemas.api.model.MovieShowTime;
import spicinemas.api.type.MovieListingType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    DSLContext dslContext;

    @Test
    public void shouldInsertMovieInDb() {
        String movieName = "abcd War";
        Movie expectedMovie = new Movie(movieName, "okay", MovieListingType.NOW_SHOWING);
        movieRepo.addMovie(expectedMovie);

        Movie expectedMovie1 = movieRepo.getMovie(expectedMovie.getName());

        Movie actualMovie = movieRepo.getMovie(movieName);
        assertThat(actualMovie.getName(), is(expectedMovie1.getName()));
        assertThat(actualMovie.getExperiences(), is(expectedMovie1.getExperiences()));
        assertThat(actualMovie.getListingType(), is(expectedMovie1.getListingType()));
    }

    @Test
    public void shouldGetMovieShowTime() {
        String movieName = "caption";
        Movie expectedMovie = new Movie(movieName, "okay", MovieListingType.NOW_SHOWING);
        movieRepo.addMovie(expectedMovie);

        Integer movieId = movieRepo.getLastInsertedMovieIdByName(movieName);
        Movie actualMovie = movieRepo.getMovieById(movieId);

        MovieShowTime movieShowtime = new MovieShowTime(actualMovie.getId(), actualMovie.getName(),
                actualMovie.getExperiences(), new Date(2019, 01, 30), new Time(11, 0, 0));
        movieRepo.addMovieShowTime(movieShowtime);

        List<MovieShowTime> movieShowTimes = movieRepo.getMovieShowTimeByMovieId(movieId);
        Assert.assertEquals(1, movieShowTimes.size());
    }

}