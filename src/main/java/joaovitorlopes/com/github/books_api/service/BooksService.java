package joaovitorlopes.com.github.books_api.service;

import joaovitorlopes.com.github.books_api.model.Books;
import joaovitorlopes.com.github.books_api.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    private BooksRepository repository;


}
