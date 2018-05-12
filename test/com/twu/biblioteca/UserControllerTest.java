package com.twu.biblioteca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
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
    public void checkLibraryNumber_shouldReturnTrue_whenTheNumberExistsInTheRegistries(){
        when(userDao.findByLibraryNumber(rocio.getLibraryNumber())).thenReturn(rocio);
        assertThat(userController.checkLibraryNumber("123-1234"), is(true));
    }
}
