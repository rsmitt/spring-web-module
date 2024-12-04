package ru.edu.springweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.edu.springweb.entity.Book;
import ru.edu.springweb.service.BookService;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAll() {
        var bookList = bookService.findAll();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/findById/{id}")
    public String getById(@PathVariable Long id, Model model) {
        var book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("/save")
    public ResponseEntity<Book> getSave(@RequestBody Book book) {
        var result = bookService.save(book);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> getUpdate(@RequestBody Book book) {
        var result = bookService.update(book);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> getDelete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

}
