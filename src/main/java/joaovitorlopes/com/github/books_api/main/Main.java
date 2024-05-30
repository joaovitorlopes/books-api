package joaovitorlopes.com.github.books_api.main;

import joaovitorlopes.com.github.books_api.model.Authors;
import joaovitorlopes.com.github.books_api.model.Books;
import joaovitorlopes.com.github.books_api.model.BooksData;
import joaovitorlopes.com.github.books_api.repository.BooksRepository;
import joaovitorlopes.com.github.books_api.service.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final BooksRepository repository;
    private final String API_URL = "https://gutendex.com/books/?search=";

    private Scanner reading = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private DataConversion dataConversion = new DataConversion();
    private LanguageConversion languageConversion = new LanguageConversion();

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
        System.out.println("Enter a title of Book: ");
        var bookName = reading.nextLine();
        String searchUrl = API_URL.concat(bookName.replace(" ", "+").toLowerCase().trim());

        String json = consumeAPI.getData(searchUrl);
        String jsonBook = dataConversion.extractObjectFromJson(json, "results");

        List<BooksData> booksDTO = dataConversion.getList(jsonBook, BooksData.class);

        if (booksDTO.size() > 0) {
            Books books = new Books(booksDTO.get(0));

            Authors author = repository.findAuthorByName(books.getAuthor().getName());
            if (author != null) {
                books.setAuthor(null);
                repository.save(books);
                books.setAuthor(author);
            }
            books = repository.save(books);
            System.out.println(books);
        } else {
            System.out.println("Book not found!");
        }
    }

    private void listRegisteredBooks() {
        List<Books> books = repository.findAll();
        books.forEach(System.out::println);
    }

    private void listRegisteredAuthors() {
        List<Authors> authors = repository.searchAuthors();
        authors.forEach(System.out::println);
    }

    private void listLivingAuthorsInYear() {
        try {
            System.out.println("Enter a year: ");
            var year = reading.nextInt();
            reading.nextLine();

            List<Authors> authors = repository.searchLivingAuthors(year);
            authors.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry. Please enter an integer.");
            reading.nextLine();
        }
    }

    private void listBooksInCertainLanguage() {
        System.out.println("Enter a language: ");
        var language = languageConversion.convertLanguage(reading.nextLine());
        List<Books> books = repository.findBooksByLanguages(language);
        if (!books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.printf("There are no books in the %s language %n", language);
        }
    }
}
