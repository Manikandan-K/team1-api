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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class MovieDetailsRepositoryTest {

    @Autowired
    private MovieDetailsRepository movieDetailsRepo;

    @Autowired
    DSLContext dslContext;

    @Test
    public void test(){
        Assert.assertTrue(true);
    }
}
