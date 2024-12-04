package ru.edu.springweb.service;

import org.springframework.stereotype.Service;
import ru.edu.springweb.entity.Book;
import ru.edu.springweb.exception.BookException;
import ru.edu.springweb.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new BookException("Книга не найдена. Не верный id"));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

}
