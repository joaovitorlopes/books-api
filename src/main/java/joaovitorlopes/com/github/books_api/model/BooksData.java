package joaovitorlopes.com.github.books_api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(@JsonAlias("title") String title, @JsonAlias("authors") List<AuthorsData> authors,
                        @JsonAlias("languages") List<String> languages,
                        @JsonAlias("download_count") Integer downloadsNumber) {

}
