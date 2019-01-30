package spicinemas.api.model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieShowTime {
	private Long movieId;
	private String name;
	private String experiences;
	private Date movieDate;
	private Time movieTime;
}
