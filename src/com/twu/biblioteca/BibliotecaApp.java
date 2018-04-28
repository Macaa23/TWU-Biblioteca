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

    public String listAllBooks() {
        String bookList = "";
        Book tempBook;
        LinkedList<Book> allBooks;
        allBooks = bibliotecaAppDao.getBooks();
        for (int i = 0; i < allBooks.size(); i++){
            tempBook = allBooks.get(i);
            bookList+= tempBook.getName() + "     " + tempBook.getAuthor()+ "     " + tempBook.getYear() + "\n";
        }
        return bookList;
    }
}
