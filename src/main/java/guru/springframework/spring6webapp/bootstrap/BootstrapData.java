package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * We are using @CommandLineRunner interface because it provides a method(run) that runs everytime spring application starts-up.
 */
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    /**
     * In this method some static data has been created. Author and Book are initialized and associated and persisted into hibernate.
     */
    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setName("Domain Driven Design");
        ddd.setIsbn("123456");

        Publisher p = new Publisher();
        p.setPublisherName("Publisher 1");
        p.setAddress("Main Road");
        p.setAddress("Rajahmundry");

        Author ericSaved = authorRepository.save(eric); // this line persists(saves) author into hibernate
        Book dddSaved = bookRepository.save(ddd); // this line persists(saves) book into hibernate
        Publisher pSaved = publisherRepository.save(p);

        ericSaved.getBooks().add(dddSaved);

        authorRepository.save(ericSaved); // this line persists the association into hibernate

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
