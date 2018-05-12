package com.twu.biblioteca;

import java.util.LinkedList;

public class BibliotecaAppDaoImpl implements BibliotecaAppDao{

    LinkedList<Book> books;
    LinkedList<Movie> movies;

    public BibliotecaAppDaoImpl() {
        books = new LinkedList<Book>();
        movies = new LinkedList<Movie>();
        books.add(new Book("Dracula", "Bram Stoker", 1897, true));
        books.add(new Book("The Magicians", "Lev Grossman", 2009, true));
        books.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true));
        movies.add(new Movie("Inception", 2010, "Christopher Nolan", 8.8));
        movies.add(new Movie("Lucy", 2014, "Luc Besson", 6.4));
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

    @Override
    public LinkedList<Movie> getMovies() {
        return movies;
    }
}
