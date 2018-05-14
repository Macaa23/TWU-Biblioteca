package com.twu.biblioteca.dao;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;

import java.util.LinkedList;

public class BookDaoImpl implements BookDao {

    LinkedList<Book> books;
    LinkedList<User> draculaBorrowers;
    LinkedList<User> magiciansBorrowers;
    LinkedList<User> casaBorrowers;

    public BookDaoImpl() {
        books = new LinkedList<Book>();
        draculaBorrowers = new LinkedList<User>();
        magiciansBorrowers = new LinkedList<User>();
        casaBorrowers = new LinkedList<User>();
        books.add(new Book("Dracula", "Bram Stoker", 1897, true, draculaBorrowers));
        books.add(new Book("The Magicians", "Lev Grossman", 2009, true, magiciansBorrowers));
        books.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true, casaBorrowers));
    }

    @Override
    public LinkedList<Book> getAll() {
        return books;
    }

    @Override
    public Book findByName(String bookName) {
        for(int i = 0; i < books.size(); i++){
            if(bookName.equalsIgnoreCase(books.get(i).getName())) return books.get(i);
        }
        return null;
    }

    @Override
    public void updateBook(Book book) {
        for(int i = 0; i < books.size(); i++){
            if(book.getName().equalsIgnoreCase(books.get(i).getName())){
                books.get(i).setAvailability(book.isAvailable());
                books.get(i).addBorrower(book.getLastBorrower());
            }
        }
    }
}
