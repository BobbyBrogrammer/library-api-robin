package com.example.boilerroom.controller;

import com.example.boilerroom.dto.BookRequest;
import com.example.boilerroom.dto.BookResponse;
import com.example.boilerroom.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest request) {
        BookResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

}
