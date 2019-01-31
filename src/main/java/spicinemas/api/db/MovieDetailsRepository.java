package spicinemas.api.db;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spicinemas.api.model.MovieDetails;

@Repository
@Transactional
public class MovieDetailsRepository {

	@Autowired
	private DSLContext dsl;

	public List<MovieDetails> getMovieDetails(int movieId) {
		return dsl.select(DSL.field("MOVIE.ID").as("id"), DSL.field("MOVIE.NAME").as("name"),
				DSL.field("MOVIE.LISTING_TYPE").as("listingType"), DSL.field("MOVIE_DETAILS.CREW").as("crew"),
				DSL.field("MOVIE_DETAILS.MOVIECAST").as("cast"), DSL.field("MOVIE_DETAILS.DURATION").as("duration"),
				DSL.field("MOVIE_DETAILS.SYNOPSIS").as("synopsis"), DSL.field("MOVIE_DETAILS.LANGUAGE").as("language"),
				DSL.field("MOVIE_DETAILS.GENERE").as("genre"), DSL.field("MOVIE_DETAILS.GRADE").as("grade"))
				.from(DSL.table("MOVIE")).join(DSL.table("MOVIE_DETAILS"))
				.on(DSL.field("MOVIE_DETAILS.MOVIE_ID").eq(DSL.field("MOVIE.ID")))
				.and(DSL.field("MOVIE.ID").eq(movieId)).fetchInto(MovieDetails.class);
	}

}
