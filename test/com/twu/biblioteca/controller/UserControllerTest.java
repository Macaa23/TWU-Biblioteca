package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.UserController;
import com.twu.biblioteca.dao.UserDao;
import com.twu.biblioteca.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserController userController;


    @Test
    public void checkLibraryNumberFormat_shouldReturnTrue_whenTheNumberFollowsTheFormat(){
        String rightNumber = "576-0039";
        assertThat(userController.checkLibraryNumberFormat(rightNumber), is(true));
    }

    @Test
    public void checkLibraryNumberFormat_shouldReturnFalse_whenTheNumberDoesntFollowTheFormat(){
        String wrongNumber = "123123-4";
        assertThat(userController.checkLibraryNumberFormat(wrongNumber), is(false));
    }

    @Test
    public void login_shouldReturnNull_whenTheUserExistsAndThePasswordDoesntMatchTheUser(){
        String incorrectPassword = "password";
        when(userDao.findByLibraryNumber(rocio.getLibraryNumber())).thenReturn(rocio);
        assertThat(userController.login(rocio.getLibraryNumber(), incorrectPassword), is(nullValue()));
    }

    @Test
    public void login_shouldReturnAnUser_whenTheUserExistsAndThePasswordMatchesTheUser(){
        when(userDao.findByLibraryNumber(rocio.getLibraryNumber())).thenReturn(rocio);
        assertThat(userController.login(rocio.getLibraryNumber(), rocio.getPassword()).getLibraryNumber(), is(rocio.getLibraryNumber()));
    }

    @Test
    public void listUserInfo_shouldReturnAStringContainingTheNameEmailAndPhoneOfAnUser_whenAnUserHasBeenInitializedWithThoseAttributes(){
        assertThat(userController.listUserInfo(rocio), is("    Your Profile\nName: "+ rocio.getName()+ "\nEmail: " +rocio.getEmail()+"\nPhone: "+rocio.getPhone()));
    }
}
