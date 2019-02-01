package spicinemas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spicinemas.api.db.MovieDetailsRepository;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.model.BookingDetails;
import spicinemas.api.model.Movie;
import spicinemas.api.model.MovieDetails;
import spicinemas.api.model.MovieShowTime;
import spicinemas.api.type.MovieListingType;

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

	@GetMapping(value = "/movies/showtimes/{movieId}")
	public List<MovieShowTime> getMoviesShowTimes(@PathVariable("movieId") Integer movieId) {
		return movieRepo.getMovieShowTimeByMovieId(movieId);
	}

	@PostMapping(value = "/movies/booking")
	public ResponseEntity<?> getTicketBookingStatus(@RequestBody BookingDetails details) {
		Integer ticketAvailable = movieRepo.getTicketsAvailable(details);
		if(ticketAvailable == 0 || ticketAvailable < details.getNumberOfTckts()){
			return new ResponseEntity<Error>(new Error("No Seats Avaialble"), HttpStatus.BAD_REQUEST);
		}
		String bookingId = movieRepo.bookMovie(details, ticketAvailable);
		return new ResponseEntity<String>(bookingId, HttpStatus.CREATED);
	}
}
