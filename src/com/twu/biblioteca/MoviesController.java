package com.twu.biblioteca;

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


}
