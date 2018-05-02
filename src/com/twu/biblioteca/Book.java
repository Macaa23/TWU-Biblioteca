package com.twu.biblioteca;

public class Book {

    private int id;
    private String name;
    private String author;
    private int year;
    private String ISBN;
    private String language;
    private String publishingHouse;
    private boolean available;

    public Book(String name, String author, int year, boolean available) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.available = available;
    }

    public Book(int id, String name, String author, int year, String ISBN, String language, String publishingHouse) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.ISBN = ISBN;
        this.language = language;
        this.publishingHouse = publishingHouse;
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

    public String getISBN() {
        return ISBN;
    }

    public String getLanguage() {
        return language;
    }

    public String getPublishingHouse() {
        return publishingHouse;
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
