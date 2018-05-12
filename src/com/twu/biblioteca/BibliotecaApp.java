package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    BooksController booksController = new BooksController();
    MoviesController moviesController = new MoviesController();

    private static int QUIT_MENU_OPTION = 6;
    private static int INVALID_OPTION = 0;

    public static void main(String[] args) {

        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        int option = 0;

        System.out.println(bibliotecaApp.getWelcomeMessage());
        do {
            System.out.println(bibliotecaApp.printMenu());
            option = bibliotecaApp.readMenuOption();
            if (option == INVALID_OPTION) continue;
            System.out.println(bibliotecaApp.executeMenuOption(option));
        } while (option != QUIT_MENU_OPTION);
    }

    public String getWelcomeMessage() {
        return "Welcome to the Bangalore Public Library System\n\n";
    }

    public ArrayList<String> getMenu() {
        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add("List Books");
        menuOptions.add("List Movies");
        menuOptions.add("Checkout Book");
        menuOptions.add("Checkout Movie");
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
            return INVALID_OPTION;
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
        Scanner input = new Scanner(System.in);
        String loanName;
        String result = "";
        if (option == 1) {
            result = booksController.listAvailableBooks();
        } else if (option == 2) {
            result = moviesController.listAvailableMovies();
        } else if (option == 3) {
            System.out.println("    \nEnter the book's name you want to checkout\n");
            loanName = input.nextLine();
            result = booksController.checkoutBook(loanName);
        } else if (option == 4) {
            System.out.println("    \nEnter the movie's name you want to checkout\n");
            loanName = input.nextLine();
            result = moviesController.checkoutMovie(loanName);
        } else if (option == 5) {
            System.out.println("    \nEnter the book's name you want to return\n");
            loanName = input.nextLine();
            result = booksController.returnBook(loanName);
        } else if (option == QUIT_MENU_OPTION) return "Execution Finished. Have a nice day :)";
        return result;
    }

}
