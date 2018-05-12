package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.util.LinkedList;

@RunWith(MockitoJUnitRunner.class)
public class MoviesControllerTest {

    private LinkedList<Movie> movies = new LinkedList<Movie>();
    Movie inception = new Movie("Inception", 2010, "Christopher Nolan", 8.8, true);
    Movie lucy = new Movie("Lucy", 2014, "Luc Besson", 6.4, false);

    @Before
    public void setUp(){
        movies.add(inception);
        movies.add(lucy);
    }

    @Mock
    private BibliotecaAppDao bibliotecaAppDao;

    @InjectMocks
    private MoviesController moviesController;


    @Test
    public void listMovies_shouldPrintTwoMovies_whenThereAreTwoMoviesRegistered(){
        when(bibliotecaAppDao.getMovies()).thenReturn(movies);
        assertThat(moviesController.listMovies().contains("Inception"), is(true));
    }

    @Test
    public void listMovies_shouldPrintErrorMessage_whenThereAreNoMoviesRegistered(){
        LinkedList<Movie> noMovies = new LinkedList<Movie>();
        when(bibliotecaAppDao.getMovies()).thenReturn(noMovies);
        assertThat(moviesController.listMovies(), is("     There Are No Books Registered\n"));
    }

    @Test
    public void listAvailableMovies_shouldReturnOneMovie_whenThereIsOneMovieAvailable(){
        when(bibliotecaAppDao.getMovies()).thenReturn(movies);
        assertThat(moviesController.listAvailableMovies().contains("Lucy"), is(false));
    }

    @Test
    public void listAvailableMovies_shouldReturnErrorMessage_whenThereAreNoMoviesAvailable(){
        inception.setAvailability(false);
        when(bibliotecaAppDao.getMovies()).thenReturn(movies);
        assertThat(moviesController.listAvailableMovies(), is("     There Are No Available Books\n"));
    }
}
