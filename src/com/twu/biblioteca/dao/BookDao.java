package com.twu.biblioteca.dao;

import com.twu.biblioteca.model.Book;

import java.util.LinkedList;

public interface BookDao {

    LinkedList<Book> getAll();

    Book findByName(String bookName);

    void updateBook(Book book);
}
