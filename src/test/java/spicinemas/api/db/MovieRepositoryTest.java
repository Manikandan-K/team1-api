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
	public void shouldInsertUserInDb() {
		String movieName = "Infinity War";
		Movie expectedMovie = new Movie(movieName, "okay", MovieListingType.NOW_SHOWING);
		movieRepo.addMovie(expectedMovie);

		MovieShowTime movieShowtime = new MovieShowTime(expectedMovie.getId(), expectedMovie.getName(),
				expectedMovie.getExperiences(), new Date(2019, 01, 30), new Time(11, 0, 0));
		movieRepo.addMovieShowTime(movieShowtime);

		Movie actualMovie = movieRepo.getMovie(movieName);
		assertThat(actualMovie.getName(), is(expectedMovie.getName()));
		assertThat(actualMovie.getExperiences(), is(expectedMovie.getExperiences()));
		assertThat(actualMovie.getListingType(), is(expectedMovie.getListingType()));
	}

	@Test
	public void shouldGetMovieShowTime() {
		String movieName = "Inception";
		Movie expectedMovie = new Movie(movieName, "okay", MovieListingType.NOW_SHOWING);
		movieRepo.addMovie(expectedMovie);

		MovieShowTime movieShowtime = new MovieShowTime(expectedMovie.getId(), expectedMovie.getName(),
				expectedMovie.getExperiences(), new Date(2019, 01, 30), new Time(11, 0, 0));
		movieRepo.addMovieShowTime(movieShowtime);

		List<MovieShowTime> movieShowTimes = movieRepo.getMovieShowTimeForName(movieName);
		Assert.assertEquals(1, movieShowTimes.size());
	}

}