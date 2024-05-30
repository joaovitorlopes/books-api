package joaovitorlopes.com.github.books_api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorsData(@JsonAlias("name") String name, @JsonAlias("birth_year") Integer birthYear,
                          @JsonAlias("death_year") Integer deathYear) {

}
