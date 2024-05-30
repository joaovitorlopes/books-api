package joaovitorlopes.com.github.books_api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Books> books = new ArrayList<>();

    public Authors() {}

    public Authors(AuthorsData authorsData) {
        this.name = authorsData.name();
        this.birthYear = authorsData.birthYear();
        this.deathYear = authorsData.deathYear();
    }

    public Authors(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "------------------- Author -----------------" +
                "\nAuthor: " + name +
                "\nBirth Year: " + birthYear +
                "\nDeath Year: " + deathYear +
                "\nLivros: " + books.stream().map(l -> l.getTitle()).collect(Collectors.toList())+
                "\n-------------------------------------------\n";
    }
}
