package com.twu.biblioteca.dao;

import com.twu.biblioteca.model.User;

import java.util.LinkedList;

public class UserDaoImpl implements UserDao {

    LinkedList<User> users;

    public UserDaoImpl() {
        users = new LinkedList<User>();
        users.add(new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238));
        users.add(new User("111-1234", "somepassword", "Macarena Carriel", "macarena@gmail.com",919235238));
    }

    @Override
    public User findByLibraryNumber(String number) {
        for(int i = 0; i < users.size(); i++){
            if(number.equalsIgnoreCase(users.get(i).getLibraryNumber())) return users.get(i);
        }
        return null;
    }
}
