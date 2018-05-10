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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    private LinkedList<Book> books = new LinkedList<Book>();
    private LinkedList<Book> emptyBookList = new LinkedList<Book>();
    private final ByteArrayOutputStream wrongInput = new ByteArrayOutputStream();
    private final ByteArrayOutputStream otherOptionInput = new ByteArrayOutputStream();
    Book dracula = new Book("Dracula", "Bram Stoker", 1897, true);
    Book theMagicians = new Book("The Magicians", "Lev Grossman", 2009, true);
    Book casa = new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true);

    @Before
    public void setUp() {
        books.add(dracula);
        books.add(theMagicians);
        books.add(casa);

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
    public void getWelcomeMessage_shouldReturnAWelcomeMessageForTheUser_whenTheUserInterfaceIsStarted(){
        BibliotecaApp userInterface = new BibliotecaApp();
        assertThat(userInterface.getWelcomeMessage(), is("Welcome to the Bangalore Public Library System\n\n"));
    }

    @Test
    public void getBooks_shouldReturnAListOfThreeBooks_whenThereAreThreeBookObjectsAddedToTheList(){
        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        assertThat(bibliotecaApp.getBooks().size(), is(books.size()));
    }

    @Test
    public void findBookByName_shouldReturnDracula_whenThereIsABookInitializedWithThatName(){
        when(bibliotecaAppDao.findByName("Dracula")).thenReturn(dracula);
        assertThat(bibliotecaApp.findBookByName("Dracula").getName(), is(dracula.getName()));
    }

    @Test
    public void findBookByName_shouldReturnNull_whenThereAreNoBooksInitializedWithTheSearchedName(){
        when(bibliotecaAppDao.findByName("No Name")).thenReturn(null);
        assertThat(bibliotecaApp.findBookByName("No Name"), is(nullValue()));
    }

    @Test
    public void listAllBooks_shouldReturnADetailedListOfThreeBooks_whenThereAreThreeBooksRegistered(){
        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        assertThat(bibliotecaApp.listAllBooks(), is("     List of all books:\n\n" + "Dracula     Bram Stoker     1897\n" +
                "The Magicians     Lev Grossman     2009\n" +
                "La Casa de los Espiritus     Isabel Allende     1982\n"));
    }

    @Test
    public void listAllBooks_shouldReturnAMessageIndicatingThatThereAreNoBooks_whenThereAreNoBooksRegistered(){
        when(bibliotecaAppDao.getBooks()).thenReturn(emptyBookList);
        assertThat(bibliotecaApp.listAllBooks(), is("     There Are No Books Registered\n"));
    }

    @Test
    public void listBooksNames_shouldReturnTheNameOfThreeBooks_whenThereAreThreeBooksRegistered(){
        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        assertThat(bibliotecaApp.listBooksNames(), is("     List of all books by name:\n\n" + "Dracula\n" +
                "The Magicians\n" +
                "La Casa de los Espiritus\n"));
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
        assertEquals("\nThank you! Enjoy the book\n", bibliotecaApp.checkoutBook("Dracula"));
    }

    @Test
    public void whenCheckoutBookIsCalledByAnUnavailableTitleLikeTheMagicians_shouldReturnAMessageIndicatingTheCheckoutWasUnsuccessful(){
        books.get(1).setAvailability(false);
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

    @Test
    public void whenABookLikeDraculaIsCheckedout_itShouldntAppearInTheBookLists(){
        when(bibliotecaAppDao.findByName("Dracula")).thenReturn(books.get(0));
        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        bibliotecaApp.checkoutBook("Dracula");
        assertFalse(bibliotecaApp.listAvailableBooks().contains("Dracula"));
    }

    @Test
    public void whenPrintMenuIsCalled_shouldReturnAStringContainingReturnBookOption(){

        assertTrue(bibliotecaApp.printMenu().contains("Return Book"));
    }

    @Test
    public void whenReturnBookIsCalledByAnUnavailableBookNameLikeDracula_shouldReturnAMessageIndicatingTheReturnWasSuccessful(){
        books.get(0).setAvailability(false);
        when(bibliotecaAppDao.findByName("Dracula")).thenReturn(books.get(0));
        assertEquals("\nThank you for returning the book.\n", bibliotecaApp.returnBook("Dracula"));
    }

    @Test
    public void whenReturnBookIsCalledByAnAvailableBookNameLikeTheMagicians_shouldReturnAMessageIndicatingTheReturnWasUnsuccessful(){
        when(bibliotecaAppDao.findByName("The Magicians")).thenReturn(books.get(1));
        assertEquals("That is not a valid book to return.", bibliotecaApp.returnBook("The Magicians"));
    }
}
