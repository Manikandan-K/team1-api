package spicinemas.api.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.model.Movie;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @InjectMocks
    private MovieController mc;

    @Mock
    private MovieRepository repoMock;

    @Before
    public void setUp(){
    }

    @Test
    public void shouldCallGetNowShowingMovies() {
        mc.getNowShowingMovies();
        verify(repoMock,times(1)).getNowShowingMovies();
    }

    @Test
    public void shouldCalladdMovie() {
        mc.init();
        verify(repoMock,times(1)).addMovie(any(Movie.class));
    }

    @After
    public void tearDown(){
        mc=null;
    }
}