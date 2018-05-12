package com.twu.biblioteca;

import java.util.LinkedList;

public interface BibliotecaAppDao {

    LinkedList<Book> getBooks();

    Book findByName(String bookName);

    void updateBook(Book book);

    LinkedList<Movie> getMovies();
}
