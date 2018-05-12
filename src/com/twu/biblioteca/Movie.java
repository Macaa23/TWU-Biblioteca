package com.twu.biblioteca;

public class Movie {

    String name;
    int year;
    String director;
    double rate;


    public Movie(String name, int year, String director, double rate) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rate = rate;
    }

    public String getName() {
        if(this.name.equals("")) return null;
        return this.name;
    }

    public int getYear() {
        if(this.name.equals("")) return 0;
        return this.year;
    }

    public String getDirector() {
        if(this.name.equals("")) return null;
        return this.director;
    }

    public double getRate() {
        if(this.name.equals("")) return 0;
        return this.rate;
    }
}
