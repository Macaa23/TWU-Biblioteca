package com.twu.biblioteca;

public class UserController {

    private UserDao userDao = new UserDaoImpl();

    public boolean checkLibraryNumber(String number) {
        userDao.findByLibraryNumber(number);
        return true;
    }
}
