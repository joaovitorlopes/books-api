package joaovitorlopes.com.github.books_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String language;
    private Integer downloadNumbers;

    public Books() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadNumbers() {
        return downloadNumbers;
    }

    public void setDownloadNumbers(Integer downloadNumbers) {
        this.downloadNumbers = downloadNumbers;
    }

    @Override
    public String toString() {
        return
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                ", downloadNumbers=" + downloadNumbers;
    }
}
