package spicinemas.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter

public class MovieShowTime {
    private Date movieDate;
    private Time movieTime;
}
