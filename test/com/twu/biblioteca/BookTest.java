package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class BookTest {


    @Test
    public void getName_shouldReturnHandOfDoom_whenBookIsInitializedWithThatBookName(){
        Book handOfDoom = new Book("Hand Of Doom", "H. P. Lovecraft", 1929, true);
        assertThat(handOfDoom.getName(), is("Hand Of Doom"));
    }

    @Test
    public void getName_shouldReturnNull_whenBookIsInitializedWithAnEmptyChainAsName(){
        Book handOfDoom = new Book("", "H. P. Lovecraft", 1929, true);
        assertThat(handOfDoom.getName(), is(nullValue()));
    }

    @Test
    public void getAuthor_shouldReturnJKRowling_whenBookIsInitializedWithThatAuthorName(){
        Book harryPotter = new Book("Harry Potter and The Half Blood Prince", "J. K. Rowling", 2007, true);
        assertThat(harryPotter.getAuthor(), is("J. K. Rowling"));
    }

    @Test
    public void getAuthor_shouldReturnNull_whenBookIsInitializedWithAnEmptyChainAsName(){
        Book harryPotter = new Book("", "J. K. Rowling", 2007, true);
        assertThat(harryPotter.getAuthor(), is(nullValue()));
    }

    @Test
    public void getAuthor_shouldReturnNull_whenBookIsInitializedWithAnEmptyChainAsAuthor(){
        Book harryPotter = new Book("Harry Potter and The Half Blood Prince", "", 2007, true);
        assertThat(harryPotter.getAuthor(), is(nullValue()));
    }

    @Test
    public void getYear_shouldReturn1897_whenBookIsInitializedWithThatYearOfRelease(){
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true);
        assertThat(dracula.getYear(), is(1897));
    }

    @Test
    public void getYear_shouldReturnZero_whenBookIsInitializedWithAnEmptyChainAsName(){
        Book dracula = new Book("", "Bram Stoker", 1897, true);
        assertThat(dracula.getYear(), is(0));
    }


    @Test
    public void isAvailable_shouldReturnTrue_whenBookIsInitializedWithTrueAvailability(){
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true);
        assertThat(dracula.isAvailable(), is(true));
    }

    @Test
    public void isAvailable_shouldReturnFalse_whenBookIsInitializedWithFalseAvailability(){
        Book theMagicians = new Book("The Magicians", "Lev Grossman", 2009, false);
        assertThat(theMagicians.isAvailable(), is(false));
    }

}
