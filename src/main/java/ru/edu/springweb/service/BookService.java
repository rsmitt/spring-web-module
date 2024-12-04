package ru.edu.springweb.service;

import ru.edu.springweb.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    Book update(Book book);

    void delete(Long id);

}
