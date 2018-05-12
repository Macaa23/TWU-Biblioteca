package com.twu.biblioteca;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    User rocio = new User("123-1234", "supersafepass", "Rocio Sepulveda", "rsepulve@thoughtworks.com",91955238);

    @Test
    public void getLibraryNumber_shouldReturn1231234_whenThereIsAnUserInitializedWithThatLibraryNumber(){
        assertThat(rocio.getLibraryNumber(), is("123-1234"));
    }

    @Test
    public void getPassword_shouldReturnSupersafepass_whenThereIsAnUserInitializedWithThatPassword(){
        assertThat(rocio.getPassword(), is("supersafepass"));
    }

    @Test
    public void getName_shouldReturnRocioSepulveda_whenThereIsAnUserInitializedWithThatName(){
        assertThat(rocio.getName(), is("Rocio Sepulveda"));
    }

    @Test
    public void getEmail_shouldReturnrsepulveatthoughtworkscom_whenThereIsAnUserInitializedWithThatEmail(){
        assertThat(rocio.getEmail(), is("rsepulve@thoughtworks.com"));
    }

    @Test
    public void getPhone_shouldReturn91955238_whenThereIsAnUserInitializedWithThatPhoneNumber(){
        assertThat(rocio.getPhone(), is(91955238));
    }

}
