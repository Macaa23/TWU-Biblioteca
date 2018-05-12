package com.twu.biblioteca;

import java.util.LinkedList;

public class BooksController {

    private BibliotecaAppDao bibliotecaAppDao = new BibliotecaAppDaoImpl();

    public Book findBookByName(String bookName) {
        return bibliotecaAppDao.findByName(bookName);
    }

    public String listAllBooks() {
        LinkedList<Book> allBooks = bibliotecaAppDao.getBooks();
        if(allBooks.isEmpty()) return "     There Are No Books Registered\n";
        String bookList = "     List of all books:\n\n";
        Book tempBook;
        for (int i = 0; i < allBooks.size(); i++) {
            tempBook = allBooks.get(i);
            bookList += tempBook.getName() + "     " + tempBook.getAuthor() + "     " + tempBook.getYear() + "\n";
        }
        return bookList;
    }

    public String listAvailableBooks() {
        LinkedList<Book> allBooks = bibliotecaAppDao.getBooks();
        String bookList = "     List of all available books:\n\n";
        Book tempBook;
        for (int i = 0; i < allBooks.size(); i++) {
            tempBook = allBooks.get(i);
            if(tempBook.isAvailable()) {
                bookList += tempBook.getName() + "     " + tempBook.getAuthor() + "     " + tempBook.getYear() + "\n";
            }
        }
        return bookList;
    }

    public String checkoutBook(String bookName) {
        Book requiredBook = findBookByName(bookName);
        if (requiredBook != null) {
            if (requiredBook.isAvailable()) {
                requiredBook.setAvailability(false);
                bibliotecaAppDao.updateBook(requiredBook);
                return "\nThank you! Enjoy the book\n";
            } else return "That book is not available.";
        } else {
            return "That book is not in the library registries.";
        }
    }

    public String returnBook(String bookName) {
        Book requiredBook = findBookByName(bookName);
        if (requiredBook != null) {
            if (!requiredBook.isAvailable()) {
                requiredBook.setAvailability(true);
                bibliotecaAppDao.updateBook(requiredBook);
                return "\nThank you for returning the book.\n";
            } else return "That is not a valid book to return.";
        } else {
            return "That book is not in the library registries.";
        }
    }
}
