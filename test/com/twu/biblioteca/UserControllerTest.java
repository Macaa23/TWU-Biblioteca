package com.twu.biblioteca;

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

/*
    @Test
    public void login_shouldReturnNull_whenTheUserDoesNotExists(){
        String falseNumber = "000-0000";
        String falsePassword = "password";
        when(userDao.findByLibraryNumber(falseNumber)).thenReturn(null);
        assertThat(userController.login(falseNumber, falsePassword), is("\nThe library number or password are incorrect.\n"));
    }

    @Test
    public void login_shouldReturnAFailureMessage_whenTheUserExistsAndThePasswordDoesntMatchTheUser(){
        String incorrectPassword = "password";
        when(userDao.findByLibraryNumber(rocio.getLibraryNumber())).thenReturn(rocio);
        assertThat(userController.login(rocio.getLibraryNumber(), incorrectPassword), is("\nThe library number or password are incorrect.\n"));
    }


*/
}
