package spicinemas.api.db;

import java.util.List;
import java.util.UUID;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.Results;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spicinemas.api.model.BookingDetails;
import spicinemas.api.model.Movie;
import spicinemas.api.model.MovieShowTime;
import spicinemas.api.type.MovieListingType;

@Repository
@Transactional
public class MovieRepository {
	@Autowired
	private DSLContext dsl;

	public void addMovie(Movie movie) {
		dsl.insertInto(DSL.table("MOVIE"), DSL.field("NAME"), DSL.field("EXPERIENCES"), DSL.field("LISTING_TYPE"))
				.values(movie.getName(), movie.getExperiences(), movie.getListingType().toString()).execute();

	}

	public Movie getMovie(String name) {
		return dsl.select(DSL.field("NAME"), DSL.field("EXPERIENCES"), DSL.field("LISTING_TYPE"))
				.from(DSL.table("MOVIE")).where(DSL.field("MOVIE.NAME").eq(name)).fetchAny().into(Movie.class);
	}

	public List<Movie> getMoviesFilteredOnListingType(MovieListingType listingType) {
		return dsl.select().from(DSL.table("MOVIE")).where(DSL.field("LISTING_TYPE").eq(listingType.toString()))
				.orderBy(DSL.field("MOVIE.NAME")).fetchInto(Movie.class);

	}

	public Movie getMovieById(int movieId) {
		return dsl.select(DSL.field("ID"), DSL.field("NAME"), DSL.field("EXPERIENCES"), DSL.field("LISTING_TYPE"))
				.from(DSL.table("MOVIE")).where(DSL.field("MOVIE.ID").eq(movieId)).fetchAny().into(Movie.class);
	}

	public int getLastInsertedMovieIdByName(String movieName) {
		Results many = dsl
				.select(DSL.field("ID"), DSL.field("NAME"), DSL.field("EXPERIENCES"), DSL.field("LISTING_TYPE"))
				.from(DSL.table("MOVIE")).where(DSL.field("MOVIE.NAME").eq(movieName)).orderBy(DSL.field("MOVIE.ID"))
				.fetchMany();
		Result<Record> recordResult = many.get(many.size() - 1);
		String myfield = recordResult.get(recordResult.size() - 1).getValue(0).toString();

		return Integer.parseInt(myfield);
	}

	public void addMovieShowTime(MovieShowTime movieShowTime) {
		dsl.insertInto(DSL.table("MOVIE_SHOWTIMES"), DSL.field("MOVIE_ID"), DSL.field("MOVIE_TIME"),
				DSL.field("MOVIE_DATE"))
				.values(movieShowTime.getMovieId(), movieShowTime.getMovieTime(), movieShowTime.getMovieDate())
				.execute();

	}

	public List<MovieShowTime> getMovieShowTimeByMovieId(int movieId) {
		List<MovieShowTime> movieShowTimes = dsl
				.select(DSL.field("MOVIE.ID").as("movieId"), DSL.field("MOVIE.NAME").as("name"),
						DSL.field("MOVIE_SHOWTIMES.ID").as("id"), DSL.field("MOVIE.EXPERIENCES").as("experiences"),
						DSL.field("MOVIE_SHOWTIMES.MOVIE_DATE").as("movieDate"),
						DSL.field("MOVIE_SHOWTIMES.MOVIE_TIME").as("movieTime"),
						DSL.field("MOVIE_THEATRE.THEATRE_NAME").as("cinema"),
						DSL.field("MOVIE_THEATRE.ID").as("theatreId"),
						DSL.field("MOVIE_THEATRE.SCREEN_NAME").as("screen"),
						DSL.field("MOVIE_THEATRE.TICKETS_COUNT").as("count"),
						DSL.field("MOVIE_THEATRE.TICKETS_SOLD").as("booked"))
				.from(DSL.table("MOVIE")).join(DSL.table("MOVIE_SHOWTIMES"))
				.on(DSL.field("MOVIE_SHOWTIMES.MOVIE_ID").eq(DSL.field("MOVIE.ID")))
				.and(DSL.field("MOVIE.ID").eq(movieId)).join(DSL.table("MOVIE_THEATRE"))
				.on(DSL.field("MOVIE_THEATRE.MOVIE_SHOWTIME_ID").eq(DSL.field("MOVIE_SHOWTIMES.ID")))
				.fetchInto(MovieShowTime.class);
		return movieShowTimes;
	}
//
//	public List<MovieShowTime> getMovieShowTimeByShowtimeId(long showTimeId) {
//		List<MovieShowTime> movieShowTimes = dsl
//				.select(DSL.field("MOVIE.ID").as("movieId"), DSL.field("MOVIE.NAME").as("name"),
//						DSL.field("MOVIE_SHOWTIMES.ID").as("id"), DSL.field("MOVIE.EXPERIENCES").as("experiences"),
//						DSL.field("MOVIE_SHOWTIMES.MOVIE_DATE").as("movieDate"),
//						DSL.field("MOVIE_SHOWTIMES.MOVIE_TIME").as("movieTime"),
//						DSL.field("MOVIE_THEATRE.THEATRE_NAME").as("cinema"),
//						DSL.field("MOVIE_THEATRE.SCREEN_NAME").as("screen"),
//						DSL.field("MOVIE_THEATRE.TICKETS_COUNT").as("count"),
//						DSL.field("MOVIE_THEATRE.TICKETS_SOLD").as("booked"))
//				.from(DSL.table("MOVIE")).join(DSL.table("MOVIE_SHOWTIMES"))
//				.on(DSL.field("MOVIE_SHOWTIMES.MOVIE_ID").eq(DSL.field("MOVIE.ID")))
//				.and(DSL.field("MOVIE_SHOWTIMES.ID").eq(showTimeId)).join(DSL.table("MOVIE_THEATRE"))
//				.on(DSL.field("MOVIE_THEATRE.MOVIE_SHOWTIME_ID").eq(DSL.field("MOVIE_SHOWTIMES.ID")))
//				.fetchInto(MovieShowTime.class);
//		return movieShowTimes;
//	}

	public String bookMovie(BookingDetails bookingDetails, int ticketAvailable) {
		String bookingId = UUID.randomUUID().toString();
		ticketAvailable -= bookingDetails.getNumberOfTckts();
		int ticketSold = getTicketsSold(bookingDetails.getShowtimeId(), bookingDetails.getTheatreId())
				+ bookingDetails.getNumberOfTckts();
		dsl.insertInto(DSL.table("MOVIE_TICKET"), DSL.field("THEATRE_ID"), DSL.field("MOVIE_SHOWTIME_ID"),
				DSL.field("BOOKING_ID"), DSL.field("USER_NAME"), DSL.field("USER_EMAIL"), DSL.field("TICKET_COUNT"))
				.values(bookingDetails.getTheatreId(), bookingDetails.getShowtimeId(), bookingId,
						bookingDetails.getUsername(), bookingDetails.getUseremail(), bookingDetails.getNumberOfTckts())
				.execute();
		dsl.update(DSL.table("MOVIE_THEATRE")).set(DSL.field("TICKETS_SOLD"), ticketSold)
				.set(DSL.field("TICKETS_COUNT"), ticketAvailable).execute();
		return bookingId;

	}

	private int getTicketsSold(Long showtimeId, Long theatreId) {

		return (Integer) dsl.select(DSL.field("TICKETS_SOLD")).from(DSL.table("MOVIE_THEATRE"))
				.where(DSL.field("ID").eq(theatreId).and(DSL.field("MOVIE_SHOWTIME_ID").eq(showtimeId))).fetch().get(0)
				.value1();
	}

	public Integer getTicketsAvailable(BookingDetails bookingDetails) {
		return (Integer) dsl.select(DSL.field("TICKETS_COUNT")).from(DSL.table("MOVIE_THEATRE"))
				.where(DSL.field("ID").eq(bookingDetails.getTheatreId())
						.and(DSL.field("MOVIE_SHOWTIME_ID").eq(bookingDetails.getShowtimeId())))
				.fetch().get(0).value1();

	}
}
