package com.example.boilerroom.dto;
import java.util.List;

public class BookResponseV2 {
        private String title;
        private String author;
        private String isbn;
        private int publishedYear;
        private Long id;
        private boolean available;

        // Getters
        public String getTitle() {return title;}
        public String getAuthor() {return author;}
        public String getIsbn() {return isbn;}
        public int getPublishedYear() {return publishedYear;}
        public Long getId() {return id;}
        public boolean isAvailable() {return available;}

        // Setters
        public void setTitle(String title) {this.title = title;}
        public void setAuthor(String author) {this.author = author;}
        public void setIsbn(String isbn) {this.isbn = isbn;}
        public void setPublishedYear(int publishedYear) {this.publishedYear = publishedYear;}
        public void setId(Long id) {this.id = id;}
        public void setAvailable(boolean available) {this.available = available;}
}
