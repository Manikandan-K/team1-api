package spicinemas.api.model;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieShowTime {

	private Long id;
	private String name;
	private String experiences;
	private Date movieDate;
	private Time movieTime;
}
