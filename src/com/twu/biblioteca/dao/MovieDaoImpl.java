package com.twu.biblioteca.dao;

import com.twu.biblioteca.model.Movie;

import java.util.LinkedList;

public class MovieDaoImpl implements MovieDao {

    LinkedList<Movie> movies;

    public MovieDaoImpl(){
        movies = new LinkedList<Movie>();
        movies.add(new Movie("Inception", 2010, "Christopher Nolan", 8.8, true));
        movies.add(new Movie("Lucy", 2014, "Luc Besson", 6.4, true));
    }

    @Override
    public LinkedList<Movie> getAll() {
        return movies;
    }

    @Override
    public Movie findByName(String name) {
        for(int i = 0; i < movies.size(); i++){
            if(name.equalsIgnoreCase(movies.get(i).getName())) return movies.get(i);
        }
        return null;
    }

    @Override
    public void updateMovie(Movie movie) {
        for(int i = 0; i < movies.size(); i++){
            if(movie.getName().equalsIgnoreCase(movies.get(i).getName())){
                movies.get(i).setAvailability(movie.isAvailable());
            }
        }
    }
}
