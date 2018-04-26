package com.twu.biblioteca;

import java.util.LinkedList;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bangalore Public Library System";
    }

    public LinkedList<Book> getBooks() {
        LinkedList<Book> availableBooks = new LinkedList<Book>();
        availableBooks.add(new Book());
        availableBooks.add(new Book());
        availableBooks.add(new Book());
        return availableBooks;
    }
}
