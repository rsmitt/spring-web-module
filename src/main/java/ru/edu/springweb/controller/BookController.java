package ru.edu.springweb.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.edu.springweb.entity.Book;
import ru.edu.springweb.service.BookServiceImp;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookServiceImp bookServiceImp;

    public BookController(BookServiceImp bookServiceImp) {
        this.bookServiceImp = bookServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookServiceImp.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable("bookId") Long bookId) {
        if (bookServiceImp.getBookById(bookId).isPresent()) {
            return new ResponseEntity<>(Objects.requireNonNull(bookServiceImp.getBookById(bookId).orElse(null)), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookServiceImp.saveBook(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/books" + book.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        bookServiceImp.updateBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable("bookId") Long bookId) {
        bookServiceImp.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
