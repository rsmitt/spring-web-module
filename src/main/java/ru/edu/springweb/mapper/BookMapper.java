package ru.edu.springweb.mapper;

import org.springframework.stereotype.Component;
import ru.edu.springweb.DTO.BookDTO;
import ru.edu.springweb.entity.Book;

import java.util.List;

@Component
public class BookMapper {

    public List<BookDTO> listBook2ListBootDTO(List<Book> books) {
        return books.stream()
                .map(this::book2bookDTO)
                .toList();
    }

    public BookDTO book2bookDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor());
    }

    public Book bookDTO2book(BookDTO bookDTO) {
        return new Book(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor());
    }
}
