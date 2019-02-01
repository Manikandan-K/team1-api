package spicinemas.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDetails {
	private Long showtimeId;
	private Long theatreId;
	private String username;
	private String useremail;
	private Integer numberOfTckts;
}