package joaovitorlopes.com.github.books_api.repository;

import joaovitorlopes.com.github.books_api.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long>{
    Books findByTitle(String title);

    @Query("SELECT b FROM Books b WHERE b.language LIKE %:language%")
    List<Books> findBooksByLanguages(String language);
}
