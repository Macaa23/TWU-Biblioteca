package com.twu.biblioteca;

import java.util.LinkedList;

public interface BookDao {

    LinkedList<Book> getAll();

    Book findByName(String bookName);

    void updateBook(Book book);
}
