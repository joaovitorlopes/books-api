package joaovitorlopes.com.github.books_api.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date yearOfBirth;
    private Date yearOfDeath;

    public Authors() {}

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

    public Date getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Date yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Date getYearOfDeath() {
        return yearOfDeath;
    }

    public void setYearOfDeath(Date yearOfDeath) {
        this.yearOfDeath = yearOfDeath;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", yearOfDeath=" + yearOfDeath;
    }
}
