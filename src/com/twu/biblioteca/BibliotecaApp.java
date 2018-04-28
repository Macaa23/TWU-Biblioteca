package com.twu.biblioteca;

import java.util.LinkedList;

public class BibliotecaApp {

    private BibliotecaAppDao bibliotecaAppDao;

    public static void main(String[] args) {

        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        System.out.println(bibliotecaApp.getWelcomeMessage());
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bangalore Public Library System\n\n";
    }

    public LinkedList<Book> getBooks() {
        return bibliotecaAppDao.getBooks();
    }

    public Book findBookByName(String bookName) {
        return bibliotecaAppDao.findBookByName(bookName);
    }

    public String listAllBooks(LinkedList<Book> allBooks) {
        String bookList = "";
        Book tempBook;
        for (int i = 0; i < allBooks.size(); i++){
            tempBook = allBooks.get(i);
            bookList+= tempBook.getName() + "     " + tempBook.getAuthor()+ "     " + tempBook.getYear() + "\n";
        }
        return bookList;
    }

    public String listBooksNames(LinkedList<Book> allBooks) {
        String bookList = "";
        Book tempBook;
        for (int i = 0; i < allBooks.size(); i++){
            tempBook = allBooks.get(i);
            bookList+= tempBook.getName()+"\n";
        }
        return bookList;
    }
}
