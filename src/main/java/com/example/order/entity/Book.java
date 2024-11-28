package com.example.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    private Long id;
    @JsonProperty("ISBN")
    private String ISBN;
    private String title;
    private String description;
    private String author;

    // Constructors
    public Book() {}

    public Book(Long id, String ISBN, String title, String description, String author) {
        this.id = id;
        this.ISBN = ISBN;
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}



