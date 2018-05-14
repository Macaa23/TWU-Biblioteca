package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.dao.MovieDao;
import com.twu.biblioteca.dao.MovieDaoImpl;

import java.util.LinkedList;

public class MoviesController {

    private MovieDao movieDao = new MovieDaoImpl();

    public String listMovies() {
        LinkedList<Movie> movies = movieDao.getAll();
        if(movies.isEmpty()) return "     There Are No Books Registered\n";
        String movieList = "     List of all movies:\n\n";
        Movie tempMovie;
        for (int i = 0; i < movies.size(); i++) {
            tempMovie = movies.get(i);
            movieList += tempMovie.getName() + "     " + tempMovie.getDirector() + "     " + tempMovie.getYear() + "     " + tempMovie.getRate() +"\n";
        }
        return movieList;
    }

    public String listAvailableMovies() {
        LinkedList<Movie> movies = movieDao.getAll();
        String titleMessage = "     List of all available movies:\n\n";
        String movieList = titleMessage;
        Movie tempMovie;
        for (int i = 0; i < movies.size(); i++) {
            tempMovie = movies.get(i);
            if(tempMovie.isAvailable()) {
                movieList += tempMovie.getName() + "     " + tempMovie.getDirector() + "     " + tempMovie.getYear() + "     " + tempMovie.getRate() + "\n";
            }
        }
        if(movieList.equals(titleMessage)) return "     There Are No Available Books\n";
        return movieList;
    }


    public String checkoutMovie(String name) {
        Movie requiredMovie = movieDao.findByName(name);
        if(requiredMovie!= null) {
            if (requiredMovie.isAvailable()) {
                requiredMovie.setAvailability(false);
                movieDao.updateMovie(requiredMovie);
                return "\nThank you! Enjoy the movie\n";
            } else {
                return "\nThat movie is not available.\n";
            }
        }else {
            return "\nThat movie is not in our registries.\n";
        }
    }
}
