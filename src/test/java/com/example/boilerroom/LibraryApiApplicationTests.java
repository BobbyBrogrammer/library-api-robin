package com.example.boilerroom;

import com.example.boilerroom.dto.BookRequest;
import com.example.boilerroom.dto.BookResponse;
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

    @Test
    void postBook_shouldReturn201() {
        BookRequest request = new BookRequest();
        request.setTitle("The Hobbit");
        request.setAuthor("Tolkien");
        request.setIsbn("001");
        request.setPublishedYear(1937);

        ResponseEntity<BookResponse> response = restTemplate.postForEntity("/api/v1/books", request,
                BookResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("The Hobbit", response.getBody().getTitle());
    }

    @Test
    void getBookById_shouldReturn200() {
        // Skapa en bok först
        BookRequest request = new BookRequest();
        request.setTitle("Lord of the Rings");
        request.setAuthor("Tolkien");

        ResponseEntity<BookResponse> created = restTemplate.postForEntity("/api/v1/books", request,
                BookResponse.class);
        Long id = created.getBody().getId();

        // Hämta boken med id
        ResponseEntity<BookResponse> response = restTemplate.getForEntity("/api/v1/books/" + id,
                BookResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Lord of the Rings", response.getBody().getTitle());
    }
}