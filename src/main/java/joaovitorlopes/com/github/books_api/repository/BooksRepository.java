package joaovitorlopes.com.github.books_api.repository;

import joaovitorlopes.com.github.books_api.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BooksRepository extends JpaRepository<Books, Long>{

}
