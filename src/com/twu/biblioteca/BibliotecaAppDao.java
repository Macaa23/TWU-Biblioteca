package com.twu.biblioteca;

import java.util.LinkedList;

public interface BibliotecaAppDao {

    public LinkedList<Book> getBooks();

    public Book findBookByName(String bookName);

}
