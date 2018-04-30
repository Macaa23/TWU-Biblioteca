package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class BibliotecaApp {

    private BibliotecaAppDao bibliotecaAppDao;

    public static void main(String[] args) {

        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        System.out.println(bibliotecaApp.getWelcomeMessage());
        System.out.println(bibliotecaApp.printMenu());
        System.out.println(bibliotecaApp.readMenuOption());

    }

    public String getWelcomeMessage() {
        return "Welcome to the Bangalore Public Library System\n\n";
    }

    public LinkedList<Book> getBooks() {
        LinkedList<Book> availableBooks = new LinkedList<Book>();
        availableBooks.add(new Book("Dracula", "Bram Stoker", 1897));
        availableBooks.add(new Book("The Magicians", "Lev Grossman", 2009));
        availableBooks.add(new Book("La Casa de los Espiritus", "Isabel Allende", 1982));
        //return bibliotecaAppDao.getBooks();
        return availableBooks;
    }

    public Book findBookByName(String bookName) {
        return bibliotecaAppDao.findBookByName(bookName);
    }

    public String listAllBooks(LinkedList<Book> allBooks) {
        String bookList = "";
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
        int option = 0;
        System.out.println("Select an option number\n");
        do {
            try {
                option = Integer.parseInt(input.next());
                if(isMenuOptionValid(option)) break;
            } catch (Exception e) {
                System.err.println("Select a valid option!");
            }
        } while (option < 1 || option > getMenu().size());
        input.close();
        return option ;
    }

    public boolean isMenuOptionValid(Object option){
        int numericOption = 0;
        if(option instanceof Integer) {
            numericOption = (Integer) option;
            if (numericOption < 1 || numericOption > getMenu().size()) {
                System.err.println("Select a valid option!");
                return false;
            }
        }else if(option instanceof String) {
            System.err.println("Select a valid option!");
            return false;
        }
        return true;
    }
}
