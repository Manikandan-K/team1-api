package spicinemas.api.model;

import java.sql.Date;
import java.sql.Time;



public class MovieShowTime {
    private Long movieId;
    private String name;
    private String experiences;
    private Date movieDate;
    private Time movieTime;

    public MovieShowTime(Long movieId, String name, String experiences, Date movieDate, Time movieTime) {
        this.movieId = movieId;
        this.name = name;
        this.experiences = experiences;
        this.movieDate = movieDate;
        this.movieTime = movieTime;
    }

    public MovieShowTime() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public String getExperiences() {
        return experiences;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public Time getMovieTime() {
        return movieTime;
    }
}
