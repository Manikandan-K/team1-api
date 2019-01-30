package spicinemas.api.db;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
				.from(DSL.table("MOVIE")).where(DSL.field("MOVIE.NAME").eq(name)).fetchOne().into(Movie.class);
	}

	public List<Movie> getMoviesFilteredOnListingType(MovieListingType listingType) {
		return dsl.select().from(DSL.table("MOVIE")).where(DSL.field("LISTING_TYPE").eq(listingType.toString()))
				.orderBy(DSL.field("MOVIE.NAME")).fetchInto(Movie.class);

	}

	public List<MovieShowTime> getMoviesWithShowTime() {
		return dsl
				.select(DSL.field("MOVIE.ID").as("id"), DSL.field("MOVIE.NAME").as("name"),
						DSL.field("MOVIE.EXPERIENCES").as("experiences"),
						DSL.field("MOVIE_SHOWTIMES.MOVIE_DATE").as("movieDate"),
						DSL.field("MOVIE_SHOWTIMES.MOVIE_TIME").as("movieTime"))
				.from(DSL.table("MOVIE")).join(DSL.table("MOVIE_SHOWTIMES"))
				.on(DSL.field("MOVIE_SHOWTIMES.MOVIE_ID").eq(DSL.field("MOVIE.ID"))).fetchInto(MovieShowTime.class);
	}
}
