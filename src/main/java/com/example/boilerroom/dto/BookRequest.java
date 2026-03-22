package com.example.boilerroom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class BookRequest {

    @Schema(description = "Title of the book")
    @NotBlank
    private String title;


    @Schema(description = "Author of the book")
    @NotBlank
    private String author;

    @Schema(description = "ISBN number")
    private String isbn;

    @Schema(description = "Year the book was published")
    private int publishedYear;

    // Getters
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getIsbn() {return isbn;}
    public int getPublishedYear() {return publishedYear;}

    // Setters
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public void setPublishedYear(int publishedYear) {this.publishedYear = publishedYear;}
}
