package com.twu.biblioteca;

public class Book {

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

    public String getAuthor() {
        if(this.name.equals("") || this.name == null || this.author.equals("")) return null;
        return this.author;
    }

    public String getName() {
        if(this.name.equals("")) return null;
        return this.name;
    }

    public int getYear() {
        if(this.name.equals("")) return 0;
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
