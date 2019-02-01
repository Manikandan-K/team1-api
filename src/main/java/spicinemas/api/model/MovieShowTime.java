package spicinemas.api.model;

import java.sql.Date;
import java.sql.Time;



public class MovieShowTime {
    private Long id;
    private Long movieId;
    private String name;
    private String experiences;
    private Date movieDate;
    private Time movieTime;
    private String cinema;
    private String screen;
    private Integer count;
    private Integer booked;

    public MovieShowTime() {
    }

    public MovieShowTime(Long id, Long movieId, String name, String experiences, Date movieDate, Time movieTime, String cinema, String screen, Integer count, Integer booked) {
        this.id = id;
        this.movieId = movieId;
        this.name = name;
        this.experiences = experiences;
        this.movieDate = movieDate;
        this.movieTime = movieTime;
        this.cinema = cinema;
        this.screen = screen;
        this.count = count;
        this.booked = booked;
    }
    public MovieShowTime(Long movieId, String name, String experiences, Date movieDate, Time movieTime, String cinema, String screen, Integer count, Integer booked) {
        this.movieId = movieId;
        this.name = name;
        this.experiences = experiences;
        this.movieDate = movieDate;
        this.movieTime = movieTime;
        this.cinema = cinema;
        this.screen = screen;
        this.count = count;
        this.booked = booked;
    }

    public String getCinema() {
        return cinema;
    }

    public String getScreen() {
        return screen;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getBooked() {
        return booked;
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

    public Long getId() {
        return id;
    }
}
