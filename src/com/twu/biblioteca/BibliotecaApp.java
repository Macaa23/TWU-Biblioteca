package com.twu.biblioteca;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BibliotecaApp {

    private BibliotecaAppDao bibliotecaAppDao = new BibliotecaAppDaoImpl();

    private static int QUIT_MENU_OPTION = 4;

    public static void main(String[] args) {

        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        int option = 0;

        System.out.println(bibliotecaApp.getWelcomeMessage());
        do {
            System.out.println(bibliotecaApp.printMenu());
            option = bibliotecaApp.readMenuOption();
            if (option == 0) continue;
            System.out.println(bibliotecaApp.executeMenuOption(option));
        } while (option != QUIT_MENU_OPTION);
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bangalore Public Library System\n\n";
    }

    public LinkedList<Book> getBooks() {
        return bibliotecaAppDao.getBooks();
    }

    public Book findBookByName(String bookName) {
        return bibliotecaAppDao.findByName(bookName);
    }

    public String listAllBooks() {
        LinkedList<Book> allBooks = this.getBooks();
        if(allBooks.isEmpty()) return "     There Are No Books Registered\n";
        String bookList = "     List of all books:\n\n";
        Book tempBook;
        for (int i = 0; i < allBooks.size(); i++) {
            tempBook = allBooks.get(i);
            bookList += tempBook.getName() + "     " + tempBook.getAuthor() + "     " + tempBook.getYear() + "\n";
        }
        return bookList;
    }

    public String listAvailableBooks() {
        LinkedList<Book> allBooks = this.getBooks();
        String bookList = "     List of all available books:\n\n";
        Book tempBook;
        for (int i = 0; i < allBooks.size(); i++) {
            tempBook = allBooks.get(i);
            if(tempBook.isAvailable()) {
                bookList += tempBook.getName() + "     " + tempBook.getAuthor() + "     " + tempBook.getYear() + "\n";
            }
        }
        return bookList;
    }

    public String listBooksNames() {
        LinkedList<Book> allBooks = this.getBooks();
        if(allBooks.isEmpty()) return "     There Are No Books Registered\n";
        String bookList = "     List of all books by name:\n\n";
        Book tempBook;
        for (int i = 0; i < allBooks.size(); i++) {
            tempBook = allBooks.get(i);
            bookList += tempBook.getName() + "\n";
        }
        return bookList;
    }

    public ArrayList<String> getMenu() {
        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add("List Books");
        menuOptions.add("Checkout Book");
        menuOptions.add("Return Book");
        menuOptions.add("Quit");
        return menuOptions;
    }

    public String printMenu() {
        String menu = "        Menu\n\n";
        ArrayList<String> menuOptions;
        menuOptions = this.getMenu();
        for (int i = 0; i < menuOptions.size(); i++) {
            menu += i + 1 + ". " + menuOptions.get(i) + "\n";
        }
        return menu +
                "\nSelect an option number\n";
    }


    public int readMenuOption() {
        Scanner input = new Scanner(System.in);
        String option;
        int numericOption = 0;
        option = input.next();
        if (isMenuOptionValid(option)) {
            numericOption = Integer.parseInt(option);
            if (numericOption == QUIT_MENU_OPTION) input.close();
            return numericOption;
        } else {
            return 0;
        }
    }

    public boolean isMenuOptionValid(String option) {
        int numericOption = 0;
        try {
            numericOption = Integer.parseInt(option);
            if (numericOption < 1 || numericOption > getMenu().size()) {
                System.err.println("Select a valid option!");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Select a valid option!");
            return false;
        }
    }

    public String checkoutBook(String bookName) {
        Book requiredBook = this.findBookByName(bookName);
        if (requiredBook != null) {
            if (requiredBook.isAvailable()) {
                requiredBook.setAvailability(false);
                bibliotecaAppDao.updateBook(requiredBook);
                return "\nThank you! Enjoy the book\n";
            } else return "That book is not available.";
        } else {
            return "That book is not in the library registries.";
        }
    }

    public String returnBook(String bookName) {
        Book requiredBook = this.findBookByName(bookName);
        if (requiredBook != null) {
            if (!requiredBook.isAvailable()) {
                requiredBook.setAvailability(true);
                bibliotecaAppDao.updateBook(requiredBook);
                return "\nThank you for returning the book.\n";
            } else return "That is not a valid book to return.";
        } else {
            return "That book is not in the library registries.";
        }
    }

    public String executeMenuOption(int option) {
        Scanner input = new Scanner(System.in);
        String bookName;
        String result = "";
        if (option == 1) {
            result = this.listAvailableBooks();
        } else if (option == 2) {
            System.out.println("    \nEnter the book's name you want to checkout\n");
            bookName = input.nextLine();
            result = checkoutBook(bookName);
        } else if (option == 3){
            System.out.println("    \nEnter the book's name you want to return\n");
            bookName = input.nextLine();
            result = returnBook(bookName);
        } else if (option == QUIT_MENU_OPTION) return "Execution Finished. Have a nice day :)";
        return result;
    }

    public String listMovies() {
        LinkedList<Movie> movies = bibliotecaAppDao.getMovies();
        if(movies.isEmpty()) return "     There Are No Books Registered\n";
        String movieList = "     List of all movies:\n\n";
        Movie tempMovie;
        for (int i = 0; i < movies.size(); i++) {
            tempMovie = movies.get(i);
            movieList += tempMovie.getName() + "     " + tempMovie.getDirector() + "     " + tempMovie.getYear() + "     " + tempMovie.getRate() +"\n";
        }
        return movieList;
    }

    public String listAvailableMovies() {
        return null;
    }
}
