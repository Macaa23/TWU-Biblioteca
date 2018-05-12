package com.twu.biblioteca;

import java.util.LinkedList;

public interface MovieDao {

    LinkedList<Movie> getAll();

    Movie findByName();
}
