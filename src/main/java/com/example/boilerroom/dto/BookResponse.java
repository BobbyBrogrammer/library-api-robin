package com.example.boilerroom.dto;

public class BookResponse {
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
    private Long id;

    // Getters
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getIsbn() {return isbn;}
    public int getPublishedYear() {return publishedYear;}
    public Long getId() {return id;}

    // Setters
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public void setPublishedYear(int publishedYear) {this.publishedYear = publishedYear;}
    public void setId(Long id) {this.id = id;}
}
