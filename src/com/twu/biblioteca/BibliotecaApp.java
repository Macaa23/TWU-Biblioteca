package com.twu.biblioteca;

import java.util.LinkedList;

public class BibliotecaApp {

    private BibliotecaAppDao bibliotecaAppDao;

    public static void main(String[] args) {

        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        System.out.println(bibliotecaApp.getWelcomeMessage());
        System.out.println(bibliotecaApp.getMenu());
        //System.out.println(bibliotecaApp.listAllBooks(bibliotecaApp.getBooks()));

    }

    public String getWelcomeMessage() {
        return "Welcome to the Bangalore Public Library System\n\n";
    }

    public LinkedList<Book> getBooks() {
        LinkedList<Book> availableBooks = new LinkedList<Book>();
        availableBooks.add(new Book("Dracula", "Bram Stoker", 1897));
        availableBooks.add(new Book("The Magicians", "Lev Grossman", 2009));
        availableBooks.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982));
        //return bibliotecaAppDao.getBooks();
        return availableBooks;
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

    public String getMenu() {
        return "        Menu\n\n1. List Books";
    }
}
