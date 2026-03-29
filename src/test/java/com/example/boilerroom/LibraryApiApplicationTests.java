package com.example.boilerroom;

import com.example.boilerroom.dto.AuthorDTO;
import com.example.boilerroom.dto.BookRequest;
import com.example.boilerroom.dto.BookResponse;
import com.example.boilerroom.dto.LoanDTO;
import com.example.boilerroom.repository.AuthorRepository;
import com.example.boilerroom.repository.BookRepository;
import com.example.boilerroom.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LoanRepository loanRepository;

    @BeforeEach
    void setUp() {
        loanRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void postBook_shouldReturn201() {
        BookRequest request = new BookRequest();
        request.setTitle("The Hobbit");
        request.setIsbn("001");
        request.setPublishedYear(1937);

        ResponseEntity<BookResponse> response = restTemplate.postForEntity(
                "/api/v1/books", request, BookResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("The Hobbit", response.getBody().getTitle());
    }

    @Test
    void getBookById_shouldReturn200() {
        BookRequest request = new BookRequest();
        request.setTitle("Lord of the Rings");

        ResponseEntity<BookResponse> created = restTemplate.postForEntity(
                "/api/v1/books", request, BookResponse.class);

        Long id = created.getBody().getId();

        ResponseEntity<BookResponse> response = restTemplate.getForEntity(
                "/api/v1/books/" + id, BookResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Lord of the Rings", response.getBody().getTitle());
    }

    @Test
    void createAuthorAndBook_shouldReturnBookForAuthor() {
        // create author first
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Tolkien");
        ResponseEntity<AuthorDTO> authorResponse = restTemplate.postForEntity(
                "/api/v1/authors", authorDTO, AuthorDTO.class);
        Long authorId = authorResponse.getBody().getId();

        // create book and link it to the author
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("The Hobbit");
        bookRequest.setAuthorId(authorId);
        restTemplate.postForEntity("/api/v1/books", bookRequest, BookResponse.class);

        // fetch books for the author and check its there
        ResponseEntity<BookResponse[]> books = restTemplate.getForEntity(
                "/api/v1/authors/" + authorId + "/books", BookResponse[].class);

        assertEquals(HttpStatus.OK, books.getStatusCode());
        assertEquals(1, books.getBody().length);
        assertEquals("The Hobbit", books.getBody()[0].getTitle());
    }

    @Test
    void createLoan_shouldReturn201() {
        // need a book before we can loan it
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("The Hobbit");
        ResponseEntity<BookResponse> bookResponse = restTemplate.postForEntity(
                "/api/v1/books", bookRequest, BookResponse.class);
        Long bookId = bookResponse.getBody().getId();

        // loan the book and check we get 201 back
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setBookId(bookId);
        ResponseEntity<LoanDTO> loanResponse = restTemplate.postForEntity(
                "/api/v1/loans", loanDTO, LoanDTO.class);

        assertEquals(HttpStatus.CREATED, loanResponse.getStatusCode());
        assertEquals("The Hobbit", loanResponse.getBody().getBookTitle());
    }

    @Test
    void createLoanTwice_shouldReturn400() {
        // create a book
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("The Hobbit");
        ResponseEntity<BookResponse> bookResponse = restTemplate.postForEntity(
                "/api/v1/books", bookRequest, BookResponse.class);
        Long bookId = bookResponse.getBody().getId();

        // loan it once, should work fine
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setBookId(bookId);
        restTemplate.postForEntity("/api/v1/loans", loanDTO, LoanDTO.class);

        // try to loan same book again, should get 400
        ResponseEntity<String> secondLoan = restTemplate.postForEntity(
                "/api/v1/loans", loanDTO, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, secondLoan.getStatusCode());
    }
}