package ru.edu.springweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.springweb.DTO.BookDTO;
import ru.edu.springweb.entity.Book;
import ru.edu.springweb.mapper.BookMapper;
import ru.edu.springweb.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository,
                       BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getAll() {
        return bookMapper.listBook2ListBootDTO(bookRepository.findAll());
    }

    public BookDTO getById(Integer id) {
        return bookMapper.book2bookDTO(bookRepository.findById(id));
    }

    public BookDTO save(BookDTO bookDTO) {
        Book book = bookMapper.bookDTO2book(bookDTO);
        return bookMapper.book2bookDTO(bookRepository.save(book));
    }

    public void delete(Integer id){
        bookRepository.delete(id);
    }
}
