package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    private LinkedList<Book> availableBooks = new LinkedList<Book>();

    @Before
    public void setUp() throws Exception {

        availableBooks.add(new Book("Dracula", "Bram Stoker", 1897));
        availableBooks.add(new Book("The Magicians", "Lev Grossman", 2009));
        availableBooks.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982));
    }

    @Mock
    private BibliotecaAppDao bibliotecaAppDao;

    @InjectMocks
    private BibliotecaApp bibliotecaApp;

    @Test
    public void whenGetWelcomeMessageIsCalled_shouldReturnAWelcomeMessageForTheUser(){
        BibliotecaApp userInterface = new BibliotecaApp();
        assertEquals("Welcome to the Bangalore Public Library System", userInterface.getWelcomeMessage());
    }

    @Test
    public void whenGetBooksIsCalled_shouldReturnAListOfThreeBooks(){

        when(bibliotecaAppDao.getBooks()).thenReturn(availableBooks);

        assertTrue(bibliotecaApp.getBooks().size() == 3);
    }

    @Test
    public void whenfindBookByNameIsCalledByDracula_shouldReturnABookNamedDracula(){

        when(bibliotecaAppDao.findBookByName("Dracula")).thenReturn(availableBooks.get(0));

        assertEquals(availableBooks.get(0).getName(), bibliotecaApp.findBookByName("Dracula").getName());
    }

    @Test
    public void whenfindBookByNameIsCalledByTheMagicians_shouldReturnABookNamedTheMagicians(){

        when(bibliotecaAppDao.findBookByName("The Magicians")).thenReturn(availableBooks.get(1));

        assertEquals(availableBooks.get(1).getName(), bibliotecaApp.findBookByName("The Magicians").getName());
    }

    @Test
    public void whenListAllBooksIsCalled_shouldReturnADetailedListOfAllBooks(){

        when(bibliotecaAppDao.getBooks()).thenReturn(availableBooks);
        assertEquals("Dracula     Bram Stoker     1897\n" +
                "The Magicians     Lev Grossman     2009\n" +
                "La Casa de los Espiritus     Isabel Allende     1982\n", bibliotecaApp.listAllBooks());
    }
}
