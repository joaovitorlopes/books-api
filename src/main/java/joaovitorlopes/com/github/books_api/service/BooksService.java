package joaovitorlopes.com.github.books_api.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import joaovitorlopes.com.github.books_api.model.BooksData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksService {
    @JsonAlias("results")
    private List<BooksData> booksData;

    public List<BooksData> getBooksData() {
        return booksData;
    }

    public void setBooksData(List<BooksData> booksData) {
        this.booksData = booksData;
    }
}
