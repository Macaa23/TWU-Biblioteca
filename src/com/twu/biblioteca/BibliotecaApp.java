package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BibliotecaApp {

    private BibliotecaAppDao bibliotecaAppDao;
    private static int QUIT_MENU_OPTION = 2;

    public static void main(String[] args) {

        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        int option = 0;

        System.out.println(bibliotecaApp.getWelcomeMessage());
        System.out.println(bibliotecaApp.printMenu());
        System.out.println("Select an option number\n");
        do {
            option = bibliotecaApp.readMenuOption();
            if (option == 0) continue;
            System.out.println(bibliotecaApp.executeMenuOption(option));
        } while (option != QUIT_MENU_OPTION);
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bangalore Public Library System\n\n";
    }

    public LinkedList<Book> getBooks() {
        LinkedList<Book> availableBooks = new LinkedList<Book>();
        availableBooks.add(new Book("Dracula", "Bram Stoker", 1897, true));
        availableBooks.add(new Book("The Magicians", "Lev Grossman", 2009, true));
        availableBooks.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982, true));
        //return bibliotecaAppDao.getBooks();
        return availableBooks;
    }

    public Book findBookByName(String bookName) {
        return bibliotecaAppDao.findBookByName(bookName);
    }

    public String listAllBooks(LinkedList<Book> allBooks) {
        String bookList = "     List of all available books:\n\n";
        Book tempBook;
        for (int i = 0; i < allBooks.size(); i++) {
            tempBook = allBooks.get(i);
            bookList += tempBook.getName() + "     " + tempBook.getAuthor() + "     " + tempBook.getYear() + "\n";
        }
        return bookList;
    }

    public String listBooksNames(LinkedList<Book> allBooks) {
        String bookList = "";
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
        return menu;
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

    public String executeMenuOption(int option) {
        String result = "";
        if (option == 1){
            result = this.listAllBooks(this.getBooks());
        }
        else if (option == QUIT_MENU_OPTION) return "Execution Finished. Have a nice day :)";
        return result + "\nSelect an option number\n";
    }

    public String checkoutBook(String bookName) {
        Book requiredBook = this.findBookByName(bookName);
        if(requiredBook.isAvailable()) return "Thank you! Enjoy the book";
        else return "That book is not available.";
    }
}
