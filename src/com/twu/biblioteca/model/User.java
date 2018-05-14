package com.twu.biblioteca.model;

public class User {

    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private int phone;

    public User(String libraryNumber, String password, String name, String email, int phone) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }


}
