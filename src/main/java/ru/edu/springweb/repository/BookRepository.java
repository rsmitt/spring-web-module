package ru.edu.springweb.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.edu.springweb.entity.Book;
import ru.edu.springweb.exception.BookException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class BookRepository {

    private static final List<Book> BOOK_REPOSITORY = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init() {
        var book1 = new Book(1, "title1", "author1");
        var book2 = new Book(2, "title2", "author2");
        var book3 = new Book(3, "title3", "author3");
        var book4 = new Book(4, "title4", "author4");
        var book5 = new Book(5, "title5", "author5");
        BOOK_REPOSITORY.add(book2);
        BOOK_REPOSITORY.add(book3);
        BOOK_REPOSITORY.add(book4);
        BOOK_REPOSITORY.add(book5);
    }

    public List<Book> findAll() {
        return BOOK_REPOSITORY;
    }

    public Optional<Book> findById(Long id) {
        return BOOK_REPOSITORY.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    public Book save(Book book) {
        book.setId(idGeneration());
        BOOK_REPOSITORY.add(book);
        return book;
    }

    public Book update(Book book) {
        return BOOK_REPOSITORY.stream()
                .filter(item -> item.getId() == book.getId())
                .map(result -> update(result, book))
                .findFirst().orElseThrow(() -> new BookException("Ошибка во время сохранения книги"));
    }

    public void delete(Long id) {
        BOOK_REPOSITORY.removeIf(book -> book.getId() == id);
    }

    private Book update(Book searchBook, Book book) {
        if (!searchBook.getAuthor().equals(book.getAuthor())) {
            searchBook.setAuthor(book.getAuthor());
        }
        if (!searchBook.getTitle().equals(book.getTitle())) {
            searchBook.setTitle(book.getTitle());
        }
        return searchBook;
    }

    private long idGeneration() {
        var uuid = UUID.randomUUID();
        return uuid.getLeastSignificantBits();
    }

}
