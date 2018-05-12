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
public class BibliotecaAppMovieTest {

    private LinkedList<Movie> movies = new LinkedList<Movie>();
    Movie inception = new Movie("Inception", 2010, "Christopher Nolan", 8.8);
    Movie lucy = new Movie("Lucy", 2014, "Luc Besson", 6.4);

    @Before
    public void setUp(){
        movies.add(inception);
        movies.add(lucy);
    }

    @Mock
    private BibliotecaAppDao bibliotecaAppDao;

    @InjectMocks
    private BibliotecaApp bibliotecaApp;

    @Test
    public void listMovies_shouldPrintTwoMovies_whenThereAreTwoMoviesRegistered(){
        when(bibliotecaAppDao.getMovies()).thenReturn(movies);
        assertThat(bibliotecaApp.listMovies().contains("Inception"), is(true));
    }

    @Test
    public void listMovies_shouldPrintErrorMessage_whenThereAreNoMoviesRegistered(){
        LinkedList<Movie> noMovies = new LinkedList<Movie>();
        when(bibliotecaAppDao.getMovies()).thenReturn(noMovies);
        assertThat(bibliotecaApp.listMovies(), is("     There Are No Books Registered\n"));
    }

    @Test
    public void listAvailableMovies_shouldReturnOneMovie_whenThereIsOneMovieAvailable(){
        when(bibliotecaAppDao.getMovies()).thenReturn(movies);
        assertThat(bibliotecaApp.listAvailableMovies().contains("Inception"), is(false));
    }
}
