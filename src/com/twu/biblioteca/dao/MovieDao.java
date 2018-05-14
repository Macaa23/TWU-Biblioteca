package com.twu.biblioteca.dao;

import com.twu.biblioteca.model.Movie;

import java.util.LinkedList;

public interface MovieDao {

    LinkedList<Movie> getAll();

    Movie findByName(String name);

    void updateMovie(Movie movie);
}
