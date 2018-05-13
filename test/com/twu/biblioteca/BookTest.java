package com.twu.biblioteca;

import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class BookTest {

    LinkedList<User> borrowers = new LinkedList<User>();

    @Test
    public void getName_shouldReturnHandOfDoom_whenBookIsInitializedWithThatBookName(){
        Book handOfDoom = new Book("Hand Of Doom", "H. P. Lovecraft", 1929, true, borrowers);
        assertThat(handOfDoom.getName(), is("Hand Of Doom"));
    }

    @Test
    public void getName_shouldReturnNull_whenBookIsInitializedWithAnEmptyChainAsName(){
        Book handOfDoom = new Book("", "H. P. Lovecraft", 1929, true, borrowers);
        assertThat(handOfDoom.getName(), is(nullValue()));
    }

    @Test
    public void getAuthor_shouldReturnJKRowling_whenBookIsInitializedWithThatAuthorName(){
        Book harryPotter = new Book("Harry Potter and The Half Blood Prince", "J. K. Rowling", 2007, true, borrowers);
        assertThat(harryPotter.getAuthor(), is("J. K. Rowling"));
    }

    @Test
    public void getAuthor_shouldReturnNull_whenBookIsInitializedWithAnEmptyChainAsName(){
        Book harryPotter = new Book("", "J. K. Rowling", 2007, true, borrowers);
        assertThat(harryPotter.getAuthor(), is(nullValue()));
    }

    @Test
    public void getAuthor_shouldReturnNull_whenBookIsInitializedWithAnEmptyChainAsAuthor(){
        Book harryPotter = new Book("Harry Potter and The Half Blood Prince", "", 2007, true, borrowers);
        assertThat(harryPotter.getAuthor(), is(nullValue()));
    }

    @Test
    public void getYear_shouldReturn1897_whenBookIsInitializedWithThatYearOfRelease(){
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true, borrowers);
        assertThat(dracula.getYear(), is(1897));
    }

    @Test
    public void getYear_shouldReturnZero_whenBookIsInitializedWithAnEmptyChainAsName(){
        Book dracula = new Book("", "Bram Stoker", 1897, true, borrowers);
        assertThat(dracula.getYear(), is(0));
    }


    @Test
    public void isAvailable_shouldReturnTrue_whenBookIsInitializedWithTrueAvailability(){
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true, borrowers);
        assertThat(dracula.isAvailable(), is(true));
    }

    @Test
    public void isAvailable_shouldReturnFalse_whenBookIsInitializedWithFalseAvailability(){
        Book theMagicians = new Book("The Magicians", "Lev Grossman", 2009, false, borrowers);
        assertThat(theMagicians.isAvailable(), is(false));
    }

    @Test
    public void getBorrowers_shouldReturnAListOfTwoUsers_whenTwoUsersHadBorrowTheBook(){
        User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);
        User macarena = new User("124-1235", "password123", "Macarena Carriel", "macarena@gmail.com",985952122);
        borrowers.add(rocio);
        borrowers.add(macarena);
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true, borrowers);
        assertThat(dracula.getBorrowers().size(), is(borrowers.size()));
    }

    @Test
    public void getLastBorrower_shouldReturnAnUser_WhenTheBookHasBeenBorrowed(){
        User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);
        User macarena = new User("124-1235", "password123", "Macarena Carriel", "macarena@gmail.com",985952122);
        borrowers.add(rocio);
        borrowers.add(macarena);
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true, borrowers);
        assertThat(dracula.getLastBorrower().getName(), is(macarena.getName()));
    }

    @Test
    public void getLastBorrower_shouldReturnNull_WhenTheBookHasNeverBeenBorrowed(){
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true, borrowers);
        assertThat(dracula.getLastBorrower(), is(nullValue()));
    }

    @Test
    public void addBorrower_shouldAddAndReturnAnUser_whenTheBookIsBeingBorrowed(){
        User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);
        Book dracula = new Book("Dracula", "Bram Stoker", 1897, true, borrowers);
        dracula.addBorrower(rocio);
        assertThat(dracula.getLastBorrower().getLibraryNumber(), is(rocio.getLibraryNumber()));
    }

}
