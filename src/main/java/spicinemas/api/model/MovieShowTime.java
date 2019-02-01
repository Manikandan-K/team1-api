package spicinemas.api.model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
    private Long theatreId;

}
