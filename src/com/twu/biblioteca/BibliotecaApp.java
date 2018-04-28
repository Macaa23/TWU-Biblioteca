package com.twu.biblioteca;

import java.util.LinkedList;

public class BibliotecaApp {

    private BibliotecaAppDao bibliotecaAppDao;

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bangalore Public Library System";
    }

    public LinkedList<Book> getBooks() {
        return bibliotecaAppDao.getBooks();
    }

    public Book findBookByName(String bookName) {
        return bibliotecaAppDao.findBookByName(bookName);
    }
}
