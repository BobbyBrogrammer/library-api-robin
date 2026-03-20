package com.example.boilerroom.dto;

import jakarta.validation.constraints.NotBlank;

public class BookRequest {
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;

    // Getters
    @NotBlank
    public String getTitle() {return title;}
    @NotBlank
    public String getAuthor() {return author;}
    public String getIsbn() {return isbn;}
    public int getPublishedYear() {return publishedYear;}

    // Setters
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public void setPublishedYear(int publishedYear) {this.publishedYear = publishedYear;}
}
