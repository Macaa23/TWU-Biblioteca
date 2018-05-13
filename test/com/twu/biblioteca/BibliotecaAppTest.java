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
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    private LinkedList<Book> books = new LinkedList<Book>();
    private LinkedList<User> borrowers = new LinkedList<User>();
    private final ByteArrayOutputStream wrongInput = new ByteArrayOutputStream();
    private final ByteArrayOutputStream otherOptionInput = new ByteArrayOutputStream();
    String DRACULA_NAME = "Dracula";
    Book dracula = new Book(DRACULA_NAME,"Bram Stoker", 1897, true, borrowers);
    Book theMagicians = new Book("The Magicians", "Lev Grossman", 2009, true, borrowers);
    Book casa = new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true, borrowers);

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
    private BookDao bookDao;

    @Mock
    private UserController userController;

    @InjectMocks
    private BibliotecaApp bibliotecaApp;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getWelcomeMessage_shouldReturnAWelcomeMessageForTheUser(){
        BibliotecaApp userInterface = new BibliotecaApp();
        assertThat(userInterface.getWelcomeMessage(), is("Welcome to the Bangalore Public Library System\nPlease Log-in to access:\n\n"));
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
        assertThat(option, is(Integer.parseInt(input)));
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
        when(bookDao.getAll()).thenReturn(books);
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
    public void printMenu_shouldReturnAStringContainingReturnBookOption(){
        assertThat(bibliotecaApp.printMenu().contains("Return Book"), is(true));
    }

    @Test
    public void printMenu_shouldReturnAStringContainingListMoviesOption(){
        assertThat(bibliotecaApp.printMenu().contains("List Movies"), is(true));
    }

    @Test
    public void printMenu_shouldReturnAStringContainingCheckoutMovieOption(){
        assertThat(bibliotecaApp.printMenu().contains("Checkout Movie"), is(true));
    }

    @Test
    public void getUserNumber_shouldReturnAStringAskingForTheLibraryNumber(){
        assertThat(bibliotecaApp.getUserNumber(), is("Enter you library number:"));
    }

    @Test
    public void getUserPassword_shouldReturnAStringAskingForThePassword(){
        assertThat(bibliotecaApp.getUserPassword(), is("Enter you password:"));
    }

    @Test
    public void readUserInput_shouldReturn1234455_whenTheUserInputIs1234455(){
        String userInput;
        String input = "1234455";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        userInput = bibliotecaApp.readUserInput();
        assertThat(userInput, is(input));
    }

    @Test
    public void checkLogin_shouldReturnASuccessMessage_whenTheUserCredentialsExistAndAreLinked(){
        User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);
        when(userController.checkLibraryNumberFormat(rocio.getLibraryNumber())).thenReturn(true);
        when(userController.login(rocio.getLibraryNumber(), rocio.getPassword())).thenReturn(rocio);
        assertThat(bibliotecaApp.checkLogin(rocio.getLibraryNumber(), rocio.getPassword()), is("\nLog-in Successful.\n"));
    }

    @Test
    public void checkLogin_shouldReturnAFailureMessage_whenTheUserCredentialsAreNotLinked(){
        User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);
        when(userController.checkLibraryNumberFormat(rocio.getLibraryNumber())).thenReturn(true);
        assertThat(bibliotecaApp.checkLogin(rocio.getLibraryNumber(), "Wrong Password"), is("\nThe library number or password are incorrect.\n"));
    }

    @Test
    public void checkLogin_shouldReturnAnErrorMessage_whenTheLibraryNumberFormatIsNotCorrect(){
        String wrongNumber = "01020222a";
        String password = "somePass";
        when(userController.checkLibraryNumberFormat(wrongNumber)).thenReturn(false);
        assertThat(bibliotecaApp.checkLogin(wrongNumber, password), is("\nThe library number must follow the format xxx-xxxx\n"));
    }


    @Test
    public void getSession_shouldReturnAnUser_whenTheUserHasLoggedInTheApplication(){
        User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);
        when(userController.checkLibraryNumberFormat(rocio.getLibraryNumber())).thenReturn(true);
        when(userController.login(rocio.getLibraryNumber(), rocio.getPassword())).thenReturn(rocio);
        bibliotecaApp.checkLogin(rocio.getLibraryNumber(), rocio.getPassword());
        assertThat(bibliotecaApp.getSession().getLibraryNumber(), is(rocio.getLibraryNumber()));
    }

    @Test
    public void printMenu_shouldReturnAStringContainingMyInformationOptionSoTheUsersCanSeeTheirData(){
        assertThat(bibliotecaApp.printMenu().contains("My Information"), is(true));
    }



}
