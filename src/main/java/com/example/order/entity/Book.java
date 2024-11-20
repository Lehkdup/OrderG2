package com.example.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @Column(nullable = false)
    private java.lang.Long id;

    @Column(nullable = false)
    private String title;

    public Book(){}
    public Book(java.lang.Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
