package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;


public class MovieTest {

    Movie inception;
    Movie noName;
    int UNRATED;

    @Before
    public void setUp(){
        UNRATED = 0;
        inception = new Movie("Inception", 2010, "Christopher Nolan", UNRATED, true);
        noName = new Movie("", 2010, "Christopher Nolan", 5, true);
    }

    @Test
    public void getName_shouldReturnInception_whenThereIsAMovieInitializedWithThatName(){
        assertThat(inception.getName(), is("Inception"));
    }

    @Test
    public void getName_shouldReturnNull_whenThereISAMovieInitializedWithAnEmptyChainAsAName(){
        assertThat(noName.getName(), is(nullValue()));
    }

    @Test
    public void getYear_shoudldReturn2010_whenThereIsAMovieInitializedWithThatYear(){
        assertThat(inception.getYear(), is(2010));
    }

    @Test
    public void getYear_shoudlReturnZero_whenThereIsAMovieInitializedWithEmptyChainAsName(){
        assertThat(noName.getYear(), is(0));
    }

    @Test
    public void getDirector_shouldReturnChristopherNolan_whenThereIsAMovieInitializedWithThatDirector(){
        assertThat(inception.getDirector(), is("Christopher Nolan"));
    }

    @Test
    public void getDirector_shouldReturnNull_whenThereIsAMovieInitializedWithEmptyChainAsName(){
        assertThat(noName.getDirector(), is(nullValue()));
    }

    @Test
    public void getRate_shouldReturnZero_whenThereIsAMovieThatHasntBeenRated(){
        assertThat(inception.getRate(), is(0.0));
    }

    @Test
    public void getRate_shouldReturnZero_whenThereIsAMovieInitializedWithEmptyChainAsName(){
        assertThat(noName.getRate(), is(0.0));
    }

    @Test
    public void getAvailability_shouldReturnTrue_whenThereIsAMovieInitializedWithThatAvailability(){
        assertThat(inception.isAvailable(), is(true));
    }

}
