package spicinemas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spicinemas.api.db.MovieDetailsRepository;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.model.Movie;
import spicinemas.api.model.MovieDetails;
import spicinemas.api.model.MovieShowTime;
import spicinemas.api.type.MovieListingType;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {

	@Autowired
	private MovieRepository movieRepo;

	@Autowired
	private MovieDetailsRepository movieDetailsRepo;

	@GetMapping(value = "/init")
	public void init() {

		movieRepo.addMovie(new Movie("Dunkirk", "good", MovieListingType.NOW_SHOWING));
	}

	@GetMapping(value = "/movies/now-showing")
	public List<Movie> getNowShowingMovies() {
		return movieRepo.getMoviesFilteredOnListingType(MovieListingType.NOW_SHOWING);
	}

	@GetMapping(value = "/movies/upcoming")
	public List<Movie> getMoviesFilteredOnListingType() {
		return movieRepo.getMoviesFilteredOnListingType(MovieListingType.UPCOMING);
	}

	@GetMapping(value = "/movies/details/{movie-id}")
	public ResponseEntity<?> getMovieDetails(@PathVariable("movie-id") Integer id) {
		List<MovieDetails> movieDetails = movieDetailsRepo.getMovieDetails(id);
		if (movieDetails == null || movieDetails.isEmpty()) {
			return new ResponseEntity<Error>(new Error("Movie details not found"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<MovieDetails>(movieDetails.get(0), HttpStatus.OK);
	}
	@RequestMapping(value = "/movies/showtimes/{movieId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovieShowTime> getMoviesShowTimes(@PathVariable("movieId") Integer movieId) {
		return movieRepo.getMovieShowTimeByMovieId(movieId);
	}
}
