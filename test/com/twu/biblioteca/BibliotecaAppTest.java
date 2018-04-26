package com.twu.biblioteca;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    @Test
    public void whenGetWelcomeMessageIsCalled_shouldReturnAWelcomeMessageForTheUser(){
        BibliotecaApp userInterface = new BibliotecaApp();
        assertEquals("Welcome to the Bangalore Public Library System", userInterface.getWelcomeMessage());
    }

    @Test
    public void whenGetBooksIsCalled_shouldReturnAListOfThreeBooks(){
        BibliotecaApp userInterface = new BibliotecaApp();
        LinkedList<Book> availableBooks = new LinkedList<Book>();
        availableBooks.add(new Book("Dracula", "Bram Stoker", 1897));
        availableBooks.add(new Book("The Magicians", "Lev Grossman", 2009));
        availableBooks.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982));

        assertTrue(userInterface.getBooks().size() == 3);
    }
}
