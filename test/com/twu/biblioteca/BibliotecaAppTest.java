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
    String DRACULA_NAME = "Dracula";
    Book dracula = new Book(DRACULA_NAME,"Bram Stoker", 1897, true);
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

    @InjectMocks BooksController booksController;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getWelcomeMessage_shouldReturnAWelcomeMessageForTheUser(){
        BibliotecaApp userInterface = new BibliotecaApp();
        assertThat(userInterface.getWelcomeMessage(), is("Welcome to the Bangalore Public Library System\n\n"));
    }

    @Test
    public void findBookByName_shouldReturnDracula_whenThereIsABookInitializedWithThatName(){
        when(bibliotecaAppDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        assertThat(booksController.findBookByName(DRACULA_NAME).getName(), is(dracula.getName()));
    }

    @Test
    public void findBookByName_shouldReturnNull_whenThereAreNoBooksInitializedWithTheSearchedName(){
        when(bibliotecaAppDao.findByName("No Name")).thenReturn(null);
        assertThat(booksController.findBookByName("No Name"), is(nullValue()));
    }

    @Test
    public void listAllBooks_shouldReturnADetailedListOfThreeBooks_whenThereAreThreeBooksRegistered(){
        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        assertThat(booksController.listAllBooks(), is("     List of all books:\n\n" + "Dracula     Bram Stoker     1897\n" +
                "The Magicians     Lev Grossman     2009\n" +
                "La Casa de los Espiritus     Isabel Allende     1982\n"));
    }

    @Test
    public void listAllBooks_shouldReturnAMessageIndicatingThatThereAreNoBooks_whenThereAreNoBooksRegistered(){
        when(bibliotecaAppDao.getBooks()).thenReturn(emptyBookList);
        assertThat(booksController.listAllBooks(), is("     There Are No Books Registered\n"));
    }

    @Test
    public void getMenu_shouldReturnAListWithAtLeastOneElement(){
        assertFalse(bibliotecaApp.getMenu().isEmpty());
    }

    @Test
    public void printMenu_shouldReturnAStringContainingListBooksOption(){
        assertTrue(bibliotecaApp.printMenu().contains("List Books"));
    }

    @Test
    public void readMenuOption_shouldReturnNumberOne_whenUserInputIsNumberOne(){
        int option;
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        option = bibliotecaApp.readMenuOption();
        assertThat(option, is(1));
    }

    @Test
    public void isMenuOptionValid_shouldPrintErrorMessage_whenUserInputIsALetter(){
        bibliotecaApp.isMenuOptionValid("x");
        assertThat(wrongInput.toString(), is("Select a valid option!\n"));
    }

    @Test
    public void isMenuOptionValid_shouldPrintErrorMessage_whenUserInputIsAnOptionNumberThatDoesNotExists(){
        bibliotecaApp.isMenuOptionValid("-3");
        assertThat(wrongInput.toString(), is("Select a valid option!\n"));
    }

    @Test
    public void executeMenuOption_shouldReturnAListIncludingTheMagicians_whenTheInputIsOne(){
        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        assertThat(bibliotecaApp.executeMenuOption(1).contains(theMagicians.getName()), is(true));
    }

    @Test
    public void printMenu_shouldReturnAStringContainingQuitOption(){
        assertThat(bibliotecaApp.printMenu().contains("Quit"), is(true));
    }

    @Test
    public void printMenu_shouldReturnAStringContainingCheckoutBookOption(){
        assertThat(bibliotecaApp.printMenu().contains("Checkout Book"), is(true));
    }

    @Test
    public void checkoutBook_shouldReturnAMessageIndicatingTheCheckoutWasSuccessful_whenTheBookIsAvailable(){
        when(bibliotecaAppDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        assertThat(booksController.checkoutBook(DRACULA_NAME), is("\nThank you! Enjoy the book\n"));
    }

    @Test
    public void checkoutBook_shouldReturnAMessageIndicatingTheCheckoutWasUnsuccessful_whenTheBookIsUnavailable(){
        theMagicians.setAvailability(false);
        when(bibliotecaAppDao.findByName("The Magicians")).thenReturn(theMagicians);
        assertThat(booksController.checkoutBook("The Magicians"), is("That book is not available."));
    }

    @Test
    public void checkoutBook_shouldReturnAMessageIndicatingTheTitleDoesNotExistsInTheLibrary_whenTheBookHasntBeenCreated(){
        when(bibliotecaAppDao.findByName("Some Ghost Book")).thenReturn(null);
        assertThat(booksController.checkoutBook("Some Ghost Book"), is("That book is not in the library registries."));
    }

    @Test
    public void checkoutBook_shouldMakeABookUnavailable_whenTheBookExistsAndIsAvailable(){
        when(bibliotecaAppDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        booksController.checkoutBook(DRACULA_NAME);
        assertThat(dracula.isAvailable(), is(false));
    }

    @Test
    public void listAvailableBooks_shouldNotIncludeTheInformationOfBooksThatAreNotAvailable(){
        dracula.setAvailability(false);
        when(bibliotecaAppDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        when(bibliotecaAppDao.getBooks()).thenReturn(books);
        assertThat(booksController.listAvailableBooks().contains(DRACULA_NAME), is(false));
    }

    @Test
    public void printMenu_shouldReturnAStringContainingReturnBookOption(){
        assertThat(bibliotecaApp.printMenu().contains("Return Book"), is(true));
    }

    @Test
    public void returnBook_shouldReturnAMessageIndicatingTheReturnWasSuccessful_whenTheBookToReturnIsUnavailable(){
        dracula.setAvailability(false);
        when(bibliotecaAppDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        assertThat( booksController.returnBook(DRACULA_NAME), is("\nThank you for returning the book.\n"));
    }

    @Test
    public void returnBook_shouldReturnAMessageIndicatingTheReturnWasUnsuccessful_whenTheBookIsAvailable(){
        theMagicians.setAvailability(true);
        when(bibliotecaAppDao.findByName("The Magicians")).thenReturn(theMagicians);
        assertThat(booksController.returnBook("The Magicians"), is("That is not a valid book to return."));
    }
}
