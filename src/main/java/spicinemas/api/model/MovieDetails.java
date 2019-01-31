package spicinemas.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spicinemas.api.type.Grade;
import spicinemas.api.type.MovieListingType;

@Getter
@Setter
@ToString
public class MovieDetails {
	private Long id;
	private String name;
	private String crew;
	private String moviecast;
	private String duration;
	private String synopsis;
	private String language;
	private String genre;
	private Grade grade;
	private MovieListingType listingType;
}
