package ru.edu.springweb.service;

import ru.edu.springweb.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long bookId);

    boolean saveBook(Book book);

    void updateBook(Book book);

    void deleteBook(Long bookId);
}
