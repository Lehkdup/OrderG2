package com.example.order.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @ManyToMany
    private List<Book> booksInCart;

    public ShoppingCart(){}
    public ShoppingCart(List<Book> booksInCart) {
        this.booksInCart = booksInCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooksInCart() {
        return booksInCart;
    }

    public void setBooksInCart(List<Book> booksInCart) {
        this.booksInCart = booksInCart;
    }
}
