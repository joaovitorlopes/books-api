package joaovitorlopes.com.github.books_api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksData {
    @JsonAlias("title")
    private String title;
    @JsonAlias("authors")
    private List<AuthorsData> authors;
    @JsonAlias("languages")
    private List<String> languages;
    @JsonAlias("download_count")
    private Integer downloadsNumber;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorsData> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorsData> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadsNumber() {
        return downloadsNumber;
    }

    public void setDownloadsNumber(Integer downloadsNumber) {
        this.downloadsNumber = downloadsNumber;
    }
}
