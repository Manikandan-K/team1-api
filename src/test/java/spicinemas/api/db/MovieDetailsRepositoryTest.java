package spicinemas.api.db;


import org.jooq.DSLContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import spicinemas.api.model.MovieDetails;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class MovieDetailsRepositoryTest {

    @Autowired
    private MovieDetailsRepository movieDetailsRepo;

    @Test
    public void shouldGetOneMovieDetailForMovieName() {
        List<MovieDetails> details = movieDetailsRepo.getMovieDetails(1);
        Assert.assertNotNull(details);
        Assert.assertEquals(1, details.size());
        Assert.assertEquals("Kabali", details.get(0).getName());
    }
}
