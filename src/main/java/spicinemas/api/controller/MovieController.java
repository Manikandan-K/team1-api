package spicinemas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spicinemas.api.db.MovieRepository;
import spicinemas.api.model.Movie;
import spicinemas.api.model.MovieShowTime;
import spicinemas.api.type.MovieListingType;

@RestController
public class MovieController {
	@Autowired
	MovieRepository movieRepo;

	@RequestMapping(value = "/init", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void init() {

		movieRepo.addMovie(new Movie("Dunkirk", "good", MovieListingType.NOW_SHOWING));
	}

	@RequestMapping(value = "/movies/now-showing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Movie> getNowShowingMovies() {
		return movieRepo.getMoviesFilteredOnListingType(MovieListingType.NOW_SHOWING);
	}

	@RequestMapping(value = "/movies/upcoming", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Movie> getMoviesFilteredOnListingType() {
		return movieRepo.getMoviesFilteredOnListingType(MovieListingType.UPCOMING);
	}

	@RequestMapping(value = "/movies/showtimes/{movieName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovieShowTime> getMoviesShowTimes(@PathVariable("movieName") String movieName) {
		return movieRepo.getMovieShowTimeForName(movieName);
	}

}
