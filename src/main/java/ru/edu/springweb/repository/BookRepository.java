package ru.edu.springweb.repository;

import org.springframework.stereotype.Repository;
import ru.edu.springweb.entity.Book;
import ru.edu.springweb.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {

    private Integer nextId = 0;
    private final List<Book> books = new ArrayList<>(Arrays.asList(new Book(++nextId, "Hobbit", "Tolkien")));

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Book findById(Integer id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findAny().orElseThrow(() -> new EntityNotFoundException(Book.class, id));
    }

    public Book save(Book book) {
        if (book.getId() != null) {
            Book bookOld = findById(book.getId());
            books.remove(bookOld);
            books.add(book);
            return findById(book.getId());
        } else {
            Book newBook = new Book(++ nextId, book.getTitle(), book.getAuthor());
            books.add(newBook);
            return findById(newBook.getId());
        }
    }

    public void delete(Integer id) {
        Book book = findById(id);
        books.remove(book);
    }
}
