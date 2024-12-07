package ru.edu.springweb.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.edu.springweb.entity.Book;
import ru.edu.springweb.exception.BookAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    private final List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Optional<Book> getBookById(Long bookId) {
        return books.stream().filter(b -> b.getId().equals(bookId)).findFirst();
    }

    @Override
    public boolean saveBook(Book book) {
        books.stream().filter(b -> b.equals(book)).findAny().ifPresent(b -> {
            throw new BookAlreadyExistsException("book already exists");
        });
        return books.add(book);
    }

    @Override
    public void updateBook(Book book) {
        Optional<Book> bookForUpdate = getBookById(book.getId());
        bookForUpdate.ifPresent(b -> {
            b.setAuthor(book.getAuthor());
            b.setTitle(book.getTitle());
        });
    }

    @Override
    public void deleteBook(Long bookId) {
        books.removeIf(book -> book.getId().equals(bookId));
    }

    @PostConstruct
    public void init() {
        books.add(new Book(1L, "Dracula", "Bram Stoker"));
        books.add(new Book(2L, "The Million Pound Bank Note", "Mark Twain"));
        books.add(new Book(3L, "The Hound of the Baskervilles", "Arthur Conan Doyle"));
    }
}
