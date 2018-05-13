package com.twu.biblioteca;

import java.beans.beancontext.BeanContextMembershipEvent;
import java.util.LinkedList;

public class Book {

    private String name;
    private String author;
    private int year;
    private boolean available;
    private LinkedList<User> borrowers;

    public Book(String name, String author, int year, boolean available, LinkedList<User> borrowers) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.available = available;
        this.borrowers = borrowers;
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

    public LinkedList<User> getBorrowers() {
        return this.borrowers;
    }

    public User getLastBorrower() {
        return borrowers.get(borrowers.size()-1);
    }

    public void addBorrower(User borrower) {
        borrowers.add(borrower);
    }
}
