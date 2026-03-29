package com.example.boilerroom.dto;

public class AuthorDTO {
    private Long id;
    private String name;
    private int bookCount;

    // Getters
    public Long getId() {return id;}
    public String getName() {return name;}
    public int getBookCount() {return bookCount;}

    // Setters
    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setBookCount(int bookCount) {this.bookCount = bookCount;}
}
