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
import java.util.LinkedList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    private LinkedList<Book> books = new LinkedList<Book>();
    private final ByteArrayOutputStream wrongInput = new ByteArrayOutputStream();
    private final ByteArrayOutputStream otherOptionInput = new ByteArrayOutputStream();

    @Before
    public void setUp() {

        books.add(new Book("Dracula", "Bram Stoker", 1897, true));
        books.add(new Book("The Magicians", "Lev Grossman", 2009, false));
        books.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true));

        System.setErr(new PrintStream(wrongInput));
        System.setOut(new PrintStream(otherOptionInput));
    }

    @After
    public void restoreStreams() {
        System.setErr(System.err);
        System.setOut(System.out);
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

        when(bibliotecaAppDao.getBooks()).thenReturn(books);

        assertTrue(bibliotecaApp.getBooks().size() == 3);
    }

    @Test
    public void whenfindBookByNameIsCalledByDracula_shouldReturnABookNamedDracula(){

        when(bibliotecaAppDao.findByName("Dracula")).thenReturn(books.get(0));

        assertEquals(books.get(0).getName(), bibliotecaApp.findBookByName("Dracula").getName());
    }

    @Test
    public void whenfindBookByNameIsCalledByTheMagicians_shouldReturnABookNamedTheMagicians(){

        when(bibliotecaAppDao.findByName("The Magicians")).thenReturn(books.get(1));

        assertEquals(books.get(1).getName(), bibliotecaApp.findBookByName("The Magicians").getName());
    }

    @Test
    public void whenListAllBooksIsCalled_shouldReturnADetailedListOfAllBooks(){

        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        assertEquals("     List of all available books:\n\n" + "Dracula     Bram Stoker     1897\n" +
                "The Magicians     Lev Grossman     2009\n" +
                "La Casa de los Espiritus     Isabel Allende     1982\n", bibliotecaApp.listAllBooks(bibliotecaApp.getBooks()));
    }

    @Test
    public void whenListBooksNamesIsCalled_shouldReturnAListOfBooksByName(){

        when(bibliotecaAppDao.getBooks()).thenReturn(books);
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
        bibliotecaApp.isMenuOptionValid("-3");
        assertEquals("Select a valid option!\n", wrongInput.toString());
    }

    @Test
    public void whenExecuteMenuOptionIsCalledByOne_shouldReturnAListIncludingTheMagicians(){
        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        assertTrue(bibliotecaApp.executeMenuOption(1).contains("The Magicians"));
    }

    @Test
    public void whenPrintMenuIsCalled_shouldReturnAStringContainingQuitOption(){

        assertTrue(bibliotecaApp.printMenu().contains("Quit"));
    }

    @Test
    public void whenPrintMenuIsCalled_shouldReturnAStringContainingCheckoutBookOption(){

        assertTrue(bibliotecaApp.printMenu().contains("Checkout Book"));
    }

    @Test
    public void whenCheckoutBookIsCalledByAnAvailableTitleLikeDracula_shouldReturnAMessageIndicatingTheCheckoutWasSuccessful(){
        when(bibliotecaAppDao.findByName("Dracula")).thenReturn(books.get(0));
        assertEquals("Thank you! Enjoy the book", bibliotecaApp.checkoutBook("Dracula"));
    }

    @Test
    public void whenCheckoutBookIsCalledByAnUnavailableTitleLikeTheMagicians_shouldReturnAMessageIndicatingTheCheckoutWasUnsuccessful(){
        when(bibliotecaAppDao.findByName("The Magicians")).thenReturn(books.get(1));
        assertEquals("That book is not available.", bibliotecaApp.checkoutBook("The Magicians"));
    }

    @Test
    public void whenCheckoutBookIsCalledByAnInexistantTitle_shouldReturnAMessageIndicatingTheTitleDoesNotExistsInTheLibrary(){
        when(bibliotecaAppDao.findByName("Some Ghost Book")).thenReturn(null);
        assertEquals("That book is not in the library registries.", bibliotecaApp.checkoutBook("Some Ghost Book"));
    }

    @Test
    public void whenABookLikeDraculaIsCheckedout_itShouldNoLongerBeAvailable(){
        when(bibliotecaAppDao.findByName("Dracula")).thenReturn(books.get(0));
        bibliotecaApp.checkoutBook("Dracula");
        assertFalse(books.get(0).isAvailable());
    }
}
