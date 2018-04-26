package com.twu.biblioteca;

public class Book {

    private String name;
    private String author;
    private String year;
    private String ISBN;
    private String language;
    private String publishingHouse;

    public Book(String name, String author, String year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(String name, String author, String year, String ISBN, String language, String publishingHouse) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.ISBN = ISBN;
        this.language = language;
        this.publishingHouse = publishingHouse;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getName() {
        return this.name;
    }

    public String getYear() {
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
}
