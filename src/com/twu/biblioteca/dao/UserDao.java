package com.twu.biblioteca.dao;

import com.twu.biblioteca.model.User;

public interface UserDao {

    User findByLibraryNumber(String number);
}
