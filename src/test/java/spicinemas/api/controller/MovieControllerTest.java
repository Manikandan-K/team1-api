package spicinemas.api.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import spicinemas.api.db.MovieDetailsRepository;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.model.Movie;
import spicinemas.api.model.MovieDetails;
import spicinemas.api.type.MovieListingType;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @InjectMocks
    private MovieController mc;

    @Mock
    private MovieRepository repoMock;

    @Mock
    private MovieDetailsRepository detailRepoMock;

    @Before
    public void setUp(){

    }

    @Test
    public void shouldCallGetNowShowingMovies() {
        mc.getNowShowingMovies();
        verify(repoMock,times(1)).getMoviesFilteredOnListingType(any(MovieListingType.class));
    }

    @Test
    public void shouldCalladdMovie() {
        mc.init();
        verify(repoMock,times(1)).addMovie(any(Movie.class));
    }

    @Test
    public void shouldCallUpcomingMovies() {
        mc.getMoviesFilteredOnListingType();
        verify(repoMock,times(1)).getMoviesFilteredOnListingType(any(MovieListingType.class));
    }

    @Test
    public void shouldCallGetMovieDetails() {
        mc.getMovieDetails(1);
        verify(detailRepoMock,times(1)).getMovieDetails(any(Integer.class));
    }

    @Test
    public void shouldReturnErrorIfMovieDetailsNotFound() {
        Mockito.when(detailRepoMock.getMovieDetails(Mockito.anyInt())).thenReturn(Mockito.anyList());
        ResponseEntity<?> movieDetailsResponse=  mc.getMovieDetails(1);
        Assert.assertTrue(movieDetailsResponse.getBody() instanceof  Error);
    }

    @Test
    public void shouldGetCorrectMovieDetails() {
        List<MovieDetails> movieList = new ArrayList<MovieDetails>();
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setName("Kabali");
        movieList.add(movieDetails);
        Mockito.when(detailRepoMock.getMovieDetails(Mockito.anyInt())).thenReturn(movieList);
        ResponseEntity<?> movieDetailsResponse=  mc.getMovieDetails(1);
        Assert.assertEquals("Kabali", ((MovieDetails)movieDetailsResponse.getBody()).getName());
    }

    @Test
    public void shouldCallShowtimes() {
        mc.getMoviesShowTimes(any(Integer.class));
        verify(repoMock,times(1)).getMovieShowTimeByMovieId(any(Integer.class));
    }


    @After
    public void tearDown(){
        mc=null;
    }
}