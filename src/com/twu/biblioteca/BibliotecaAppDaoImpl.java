package com.twu.biblioteca;

import java.util.LinkedList;

public class BibliotecaAppDaoImpl implements BibliotecaAppDao{

    LinkedList<Book> books;

    public BibliotecaAppDaoImpl() {
        books = new LinkedList<Book>();
        books.add(new Book("Dracula", "Bram Stoker", 1897, true));
        books.add(new Book("The Magicians", "Lev Grossman", 2009, true));
        books.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true));
    }

    @Override
    public LinkedList<Book> getBooks() {
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
            }
        }
    }
}
