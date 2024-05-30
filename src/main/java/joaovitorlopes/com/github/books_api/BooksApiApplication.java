package joaovitorlopes.com.github.books_api;

import joaovitorlopes.com.github.books_api.main.Main;
import joaovitorlopes.com.github.books_api.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApiApplication implements CommandLineRunner {
	@Autowired
	private BooksRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BooksApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.showMenu();
	}

}
