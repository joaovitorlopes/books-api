package joaovitorlopes.com.github.books_api.repository;

import joaovitorlopes.com.github.books_api.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Authors, Long> {
    Authors findByName(String name);

    @Query("SELECT a FROM Authors a WHERE a.birthYear <= :year AND (a.deathYear >= :year OR a.deathYear IS NULL)")
    List<Authors> findLivingAuthors(int year);
}
