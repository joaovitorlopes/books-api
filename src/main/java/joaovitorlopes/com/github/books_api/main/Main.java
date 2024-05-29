package joaovitorlopes.com.github.books_api.main;

import joaovitorlopes.com.github.books_api.repository.BooksRepository;

import java.util.Scanner;

public class Main {

    private final BooksRepository repository;
    private Scanner reading = new Scanner(System.in);

    public Main(BooksRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        var option = -1;

        while (option != 0) {
            var menu = """
                    *** Books Search API ***
                                        
                    1- Search Books by Title
                    2- List Registered Books
                    3- List Registered Authors
                    4- List Living Authors in a Given Year
                    5- List Books in a Certain Language
                                    
                    0 - Exit
                    """;

            System.out.println(menu);
            option = reading.nextInt();
            reading.nextLine();

            switch (option) {
                case 1:
                    searchBooksByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listLivingAuthorsInYear();
                    break;
                case 5:
                    listBooksInCertainLanguage();
                    break;
                case 0:
                    System.out.println("Exiting...!");
                    break;
                default:
                    System.out.println("Invalid Entry!");
            }
        }
    }

    private void searchBooksByTitle() {

    }

    private void listRegisteredBooks() {

    }

    private void listRegisteredAuthors() {

    }

    private void listLivingAuthorsInYear() {

    }

    private void listBooksInCertainLanguage() {

    }
}
