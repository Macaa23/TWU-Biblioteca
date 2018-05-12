package com.twu.biblioteca;

public class UserController {

    private UserDao userDao = new UserDaoImpl();

    public boolean checkLibraryNumberFormat(String libraryNumber) {
        if(libraryNumber.matches("^\\d{3}\\-\\d{4}")) return true;
        else return false;
    }

    public String login(String libraryNumber, String password) {
        if(!checkLibraryNumberFormat(libraryNumber)) return "\nThe library number must follow the format: ddd-dddd\n";
        User requiredUser = userDao.findByLibraryNumber(libraryNumber);
        if(requiredUser == null || !requiredUser.getPassword().equals(password)) return "\nThe library number or password are incorrect.\n";
        else return "\nLog-in Successful.\n";
    }
}
