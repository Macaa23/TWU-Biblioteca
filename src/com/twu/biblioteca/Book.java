package com.twu.biblioteca;

public class Book {

    private int id;
    private String name;
    private String author;
    private int year;
    private boolean available;

    public Book(String name, String author, int year, boolean available) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public String toString() {
        return this.name + "   |   " + this.author + "   |   " + this.year + "   |   " ;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailability(boolean availability) {
        this.available = availability;
    }
}
