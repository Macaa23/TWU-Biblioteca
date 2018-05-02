package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookTest {

    @Test
    public void whenGetAuthorIsCalledByBookNameLaCasaDeLosEspiritus_shouldReturnIsabelAllende(){

        Book laCasaDeLosEspiritus = new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true);

        assertEquals("Isabel Allende", laCasaDeLosEspiritus.getAuthor());
    }

    @Test
    public void whenGetAuthorIsCalledByBookNameHarryPotterReturnJKRowling(){

        Book harryPotter = new Book("Harry Potter and The Half Blood Prince", "J. K. Rowling", 2007, true);

        assertEquals("J. K. Rowling", harryPotter.getAuthor());
    }

    @Test
    public void whenGetNameIsCalledByBookNameHandOfDoom_shouldReturnHandOfDoom(){

        Book handOfDoom = new Book("Hand Of Doom", "H. P. Lovecraft", 1929, true);

        assertEquals("Hand Of Doom", handOfDoom.getName());
    }

    @Test
    public void whenGetNameIsCalledByBookNameLesMiserables_shouldReturnLesMiserables(){

        Book lesMiserables = new Book("Les Miserables", "Victor Hugo", 1859, true);

        assertEquals("Les Miserables", lesMiserables.getName());
    }

    @Test
    public void whenGetYearOfReleaseIsCalledByDracula_shouldReturn1897(){

        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true);

        assertTrue(1897 == dracula.getYear());
    }

    @Test
    public void whenGetYearOfReleaseIsCalledByTheMagicians_shouldReturn2009(){

        Book theMagicians = new Book("The Magicians", "Lev Grossman", 2009, true);

        assertTrue(2009 == theMagicians.getYear());
    }

    @Test
    public void whenIsBookAvailableIsCalledByDracula_shouldReturnTrue(){
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true);

        assertTrue(dracula.isAvailable());
    }

    @Test
    public void whenIsAvailableIsCalledByTheMagicians_shouldReturnFalse(){
        Book theMagicians = new Book("The Magicians", "Lev Grossman", 2009, false);

        assertFalse(theMagicians.isAvailable());
    }


}
