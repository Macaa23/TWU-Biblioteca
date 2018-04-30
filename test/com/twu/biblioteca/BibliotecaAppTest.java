package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    private LinkedList<Book> availableBooks = new LinkedList<Book>();
    private final ByteArrayOutputStream wrongInput = new ByteArrayOutputStream();
    @Before
    public void setUp() {

        availableBooks.add(new Book("Dracula", "Bram Stoker", 1897));
        availableBooks.add(new Book("The Magicians", "Lev Grossman", 2009));
        availableBooks.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982));

        System.setErr(new PrintStream(wrongInput));
    }

    @After
    public void restoreStreams() {
        System.setErr(System.err);
    }

    @Mock
    private BibliotecaAppDao bibliotecaAppDao;

    @InjectMocks
    private BibliotecaApp bibliotecaApp;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void whenGetWelcomeMessageIsCalled_shouldReturnAWelcomeMessageForTheUser(){
        BibliotecaApp userInterface = new BibliotecaApp();
        assertEquals("Welcome to the Bangalore Public Library System\n\n", userInterface.getWelcomeMessage());
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
                "La Casa de los Espiritus     Isabel Allende     1982\n", bibliotecaApp.listAllBooks(bibliotecaApp.getBooks()));
    }

    @Test
    public void whenListBooksNamesIsCalled_shouldReturnAListOfBooksByName(){

        when(bibliotecaAppDao.getBooks()).thenReturn(availableBooks);
        assertEquals("Dracula\n" +
                "The Magicians\n" +
                "La Casa de los Espiritus\n", bibliotecaApp.listBooksNames(bibliotecaApp.getBooks()));
    }

    @Test
    public void whenGetMenuIsCalled_shouldReturnAListWithAtLeastOneElement(){
        assertFalse(bibliotecaApp.getMenu().isEmpty());
    }

    @Test
    public void whenPrintMenuIsCalled_shouldReturnAStringContainingListBooksOption(){

        assertTrue(bibliotecaApp.printMenu().contains("List Books"));
    }


    @Test
    public void whenReadMenuOptionIsCalledAndUserInputIsOne_shouldReturnNumberOne(){
        int option;
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        option = bibliotecaApp.readMenuOption();
        assertTrue(option == 1);
    }

    @Test
    public void whenIsMenuOptionValidCalledByALetter_shouldPrintErrorMessage(){
        bibliotecaApp.isMenuOptionValid("x");
        assertEquals("Select a valid option!\n", wrongInput.toString());
    }

    @Test
    public void whenIsMenuOptionValidCalledByAnInvalidNumber_shouldPrintErrorMessage(){
        bibliotecaApp.isMenuOptionValid(-3);
        assertEquals("Select a valid option!\n", wrongInput.toString());
    }
}
