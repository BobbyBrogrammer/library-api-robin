package com.example.boilerroom.service;

import com.example.boilerroom.dto.BookRequest;
import com.example.boilerroom.dto.BookResponse;
import com.example.boilerroom.model.Book;
import com.example.boilerroom.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    
    private final BookRepository repository;
    
    public BookService(BookRepository repository) {
        this.repository = repository;
    }
    public BookResponse create(BookRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getIsbn(), request.getPublishedYear());
        Book saved = repository.save(book);

        BookResponse response = new BookResponse();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setAuthor(saved.getAuthor());
        response.setIsbn(saved.getIsbn());
        response.setPublishedYear(saved.getPublishedYear());
        return response;
    }

    public List<BookResponse> getAll() {
        List<Book> books = repository.findAll();
        List<BookResponse> responses = new ArrayList<>();

        for (Book book : books) {
            BookResponse response = new BookResponse();
            response.setId(book.getId());
            response.setTitle(book.getTitle());
            response.setAuthor(book.getAuthor());
            response.setIsbn(book.getIsbn());
            response.setPublishedYear(book.getPublishedYear());
            responses.add(response);
        }
        return responses;
    }

    public BookResponse getById(Long id) {
        Optional<Book> bookOptional = repository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        
        Book book = bookOptional.get();
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setPublishedYear(book.getPublishedYear());
        return response;
    }

}
