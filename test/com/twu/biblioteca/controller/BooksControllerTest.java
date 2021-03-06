package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.BooksController;
import com.twu.biblioteca.dao.BookDao;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BooksControllerTest {

    private LinkedList<Book> books = new LinkedList<Book>();
    private LinkedList<Book> emptyBookList = new LinkedList<Book>();
    private LinkedList<User> borrowers = new LinkedList<User>();
    private String DRACULA_NAME = "Dracula";
    private Book dracula = new Book(DRACULA_NAME,"Bram Stoker", 1897, true, borrowers);
    private Book theMagicians = new Book("The Magicians", "Lev Grossman", 2009, true, borrowers);
    private Book casa = new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true, borrowers);

    @Before
    public void setUp() {
        books.add(dracula);
        books.add(theMagicians);
        books.add(casa);
    }

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private BooksController booksController;

    @Test
    public void listAllBooks_shouldReturnADetailedListOfThreeBooks_whenThereAreThreeBooksRegistered(){
        when(bookDao.getAll()).thenReturn(books);
        assertThat(booksController.listAllBooks(), is("     List of all books:\n\n" + "Dracula     Bram Stoker     1897\n" +
                "The Magicians     Lev Grossman     2009\n" +
                "La Casa de los Espiritus     Isabel Allende     1982\n"));
    }

    @Test
    public void listAllBooks_shouldReturnAMessageIndicatingThatThereAreNoBooks_whenThereAreNoBooksRegistered(){
        when(bookDao.getAll()).thenReturn(emptyBookList);
        assertThat(booksController.listAllBooks(), is("     There Are No Books Registered\n"));
    }

    @Test
    public void checkoutBook_shouldReturnAMessageIndicatingTheCheckoutWasSuccessful_whenTheBookIsAvailable(){
        when(bookDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        assertThat(booksController.checkoutBook(DRACULA_NAME, null), is("\nThank you! Enjoy the book\n"));
    }

    @Test
    public void checkoutBook_shouldReturnAMessageIndicatingTheCheckoutWasUnsuccessful_whenTheBookIsUnavailable(){
        theMagicians.setAvailability(false);
        when(bookDao.findByName("The Magicians")).thenReturn(theMagicians);
        assertThat(booksController.checkoutBook("The Magicians", null), is("That book is not available."));
    }

    @Test
    public void checkoutBook_shouldReturnAMessageIndicatingTheTitleDoesNotExistsInTheLibrary_whenTheBookHasntBeenCreated(){
        when(bookDao.findByName("Some Ghost Book")).thenReturn(null);
        assertThat(booksController.checkoutBook("Some Ghost Book", null), is("That book is not in the library registries."));
    }

    @Test
    public void checkoutBook_shouldMakeABookUnavailable_whenTheBookExistsAndIsAvailable(){
        when(bookDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        booksController.checkoutBook(DRACULA_NAME, null);
        assertThat(dracula.isAvailable(), is(false));
    }

    @Test
    public void listAvailableBooks_shouldNotIncludeTheInformationOfBooksThatAreNotAvailable(){
        dracula.setAvailability(false);
        when(bookDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        when(bookDao.getAll()).thenReturn(books);
        assertThat(booksController.listAvailableBooks().contains(DRACULA_NAME), is(false));
    }

    @Test
    public void returnBook_shouldReturnAMessageIndicatingTheReturnWasSuccessful_whenTheBookToReturnIsUnavailable(){
        dracula.setAvailability(false);
        when(bookDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        assertThat( booksController.returnBook(DRACULA_NAME), is("\nThank you for returning the book.\n"));
    }

    @Test
    public void returnBook_shouldReturnAMessageIndicatingTheReturnWasUnsuccessful_whenTheBookIsAvailable(){
        theMagicians.setAvailability(true);
        when(bookDao.findByName("The Magicians")).thenReturn(theMagicians);
        assertThat(booksController.returnBook("The Magicians"), is("That is not a valid book to return."));
    }

    @Test
    public void checkoutBook_shouldRegisterTheBorrower_whenTheBookExistsAndIsAvailable(){
        User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);
        when(bookDao.findByName(DRACULA_NAME)).thenReturn(dracula);
        booksController.checkoutBook(DRACULA_NAME, rocio);
        assertThat(dracula.getLastBorrower().getLibraryNumber(), is(rocio.getLibraryNumber()));
    }
}
