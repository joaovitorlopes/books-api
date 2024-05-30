package joaovitorlopes.com.github.books_api.main;

import joaovitorlopes.com.github.books_api.model.Authors;
import joaovitorlopes.com.github.books_api.model.AuthorsData;
import joaovitorlopes.com.github.books_api.model.Books;
import joaovitorlopes.com.github.books_api.model.BooksData;
import joaovitorlopes.com.github.books_api.repository.AuthorsRepository;
import joaovitorlopes.com.github.books_api.repository.BooksRepository;
import joaovitorlopes.com.github.books_api.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;
    private final String API_URL = "https://gutendex.com/books";

    private Scanner reading = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private BooksService booksService = new BooksService();
    private DataConversion dataConversion = new DataConversion();
    private NameConversion nameConversion = new NameConversion();
    private LanguageConversion languageConversion = new LanguageConversion();

    private List<BooksData> booksDataList = new ArrayList<>();
    private List<AuthorsData> authorsDataList = new ArrayList<>();

    public Main(BooksRepository booksRepository, AuthorsRepository authorsRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
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
        var book = searchBooks();
        if (book != null) {
            System.out.println("*****BOOK*****");
            System.out.printf("Title: %s%n",book.getTitle());
            System.out.printf("Author: %s%n", nameConversion.formatName(book.getAuthors().get(0).getName()));
            String language = String.join(", ", book.getLanguages());
            System.out.printf("Language: %s%n", language);
            System.out.printf("Downloads Number: %d%n", book.getDownloadsNumber());
            System.out.println("*****BOOK***** \n");
        } else {
            System.out.println("No books found with the given title.");
        }
    }

    private void listRegisteredBooks() {
        var books = booksRepository.findAll();
        if(books.isEmpty()) {
            System.out.println("No books registered in the database.");
        } else {
            System.out.println("*****BOOKS*****");
            for (var book : books) {
                System.out.printf("Title: %s%n",book.getTitle());
                System.out.printf("Author: %s%n", book.getAuthor());
                System.out.printf("Language: %s%n", book.getLanguage());
                System.out.printf("Downloads Number: %d%n", book.getDownloadNumbers());
                System.out.println("***************\n");
            }
        }
    }

    private void listRegisteredAuthors() {
        var authors = authorsRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("No author registered in the database.");
        } else {
            System.out.println("*****AUTHORS*****");
            for (var author : authors) {
                System.out.printf("Name: %s%n", author.getName());
                System.out.printf("Birth Year: %d%n", author.getBirthYear());
                System.out.printf("Death Year: %d%n", author.getDeathYear());
                System.out.println("****************\n");
            }
        }
    }

    private void listLivingAuthorsInYear() {
        System.out.println("Enter a year: ");
        var year = reading.nextInt();
        reading.nextLine();
        var authors = authorsRepository.findLivingAuthors(year);
        if (authors.isEmpty()) {
            System.out.println("No living authors registered in the database for the year " + year + ".");
        } else {
            System.out.println("***** AUTHORS LIVE IN" + year + "*****");
            for (var author : authors) {
                System.out.printf("Name: %s%n", author.getName());
                System.out.printf("Birth Year: %d%n", author.getBirthYear());
                System.out.printf("Death Year: %d%n", author.getDeathYear());
                System.out.println("*************************\n");
            }
        }

    }

    private void listBooksInCertainLanguage() {

    }

    private BooksData searchBooks() {
        System.out.println("Enter a books title: ");
        var title = reading.nextLine();
        var json = consumeAPI.getData(API_URL + "?search=" + title.replace(" ", "%20"));
        booksService = dataConversion.getData(json, BooksService.class);
        if (!booksService.getBooksData().isEmpty()) {
            var booksData = booksService.getBooksData().get(0);
            getBooksData(booksData);
            getAuthorsData(booksData.getAuthors());
            return booksData;
        } else {
            return null;
        }
    }

    private void getBooksData(BooksData booksData) {
        var existentBook = booksRepository.findByTitle(booksData.getTitle());
        if(existentBook == null) {
            var book = new Books();
            book.setTitle(booksData.getTitle());
            book.setAuthor(booksData.getAuthors().get(0).getName());
            book.setLanguage(String.join(", ", booksData.getLanguages()));
            book.setDownloadNumbers(booksData.getDownloadsNumber());
            booksRepository.save(book);
        }
    }

    private void getAuthorsData(List<AuthorsData> authorsData) {
        for (var authorData : authorsData) {
            var existentAuthor = authorsRepository.findByName(authorData.getName());
            if(existentAuthor == null) {
                var author = new Authors();
                author.setName(authorData.getName());
                author.setBirthYear(authorData.getBirthYear());
                author.setDeathYear(authorData.getDeathYear());
                authorsRepository.save(author);
            }
        }
    }
}
