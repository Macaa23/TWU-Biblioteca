package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.User;
import com.twu.biblioteca.dao.UserDao;
import com.twu.biblioteca.dao.UserDaoImpl;

public class UserController {

    private UserDao userDao = new UserDaoImpl();

    public boolean checkLibraryNumberFormat(String libraryNumber) {
        if(libraryNumber.matches("^\\d{3}\\-\\d{4}")) return true;
        else return false;
    }

    public User login(String libraryNumber, String password) {
        User requiredUser = userDao.findByLibraryNumber(libraryNumber);
        if(requiredUser != null && requiredUser.getPassword().equals(password)) return requiredUser;
        else return null;
    }

    public String listUserInfo(User user) {
        return "    Your Profile\nName: "+ user.getName()+ "\nEmail: " +user.getEmail()+"\nPhone: "+user.getPhone();
    }
}
