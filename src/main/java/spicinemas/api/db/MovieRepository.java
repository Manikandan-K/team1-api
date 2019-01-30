package spicinemas.api.db;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MovieRepository {
    @Autowired
    private DSLContext dsl;

    public void addMovie(Movie movie) {
        dsl.insertInto(DSL.table("MOVIE"), DSL.field("NAME"), DSL.field("EXPERIENCES"), DSL.field("LISTING_TYPE"))
                .values(movie.getName(), movie.getExperiences(), movie.getListingType().toString())
                .execute();

    }

    public Movie getMovie(String name) {
        return dsl.select(DSL.field("NAME"), DSL.field("EXPERIENCES"), DSL.field("LISTING_TYPE"))
                .from(DSL.table("MOVIE"))
                .where(DSL.field("MOVIE.NAME").eq(name))
                .fetchOne()
                .into(Movie.class);
    }

    public List<Movie> getMoviesFilteredOnListingType(MovieListingType listingType) {
        return dsl.select()
                .from(DSL.table("MOVIE"))
                .where(DSL.field("LISTING_TYPE").eq(listingType.toString()))
                .orderBy(DSL.field("MOVIE.NAME"))
                .fetchInto(Movie.class);

    }

    public List<Movie> getMoviesWithShowTime(){
        Map<Record2<String, String>,
                        List<Record2<Integer, String>>> booksByAuthor =


                dsl.select(
                        DSL.field("MOVIE.ID"),
                        DSL.field("MOVIE.NAME"),
                        DSL.field("MOVIE.EXPERIENCES"),
                        DSL.field("MOVIESHOWTIMES.DATE"),
                        DSL.field("MOVIESHOWTIMES.TIME")
                        )
                        .from(DSL.table("MOVIE"))
                        .join(DSL.table("MOVIESHOWTIMES"))
                        .on(DSL.field("MOVIESHOWTIMES.MOVIE_ID").eq(DSL.field("MOVIE.ID")))
                        .fetch()
                        .stream()
                        .collect(Collectors.groupingBy(

                                // This is the grouping key
                                r -> r.into(AUTHOR.FIRST_NAME,
                                        AUTHOR.LAST_NAME),

                                // This is the target data structure
                                LinkedHashMap::new,

                                // This is the value to be produced for each
                                // group: A list of BOOK
                                mapping(
                                        r -> r.into(BOOK.ID, BOOK.TITLE),
                                        toList()
                                )
                        ));
    }
}
