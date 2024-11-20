package com.example.order.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @ElementCollection
    //@ManyToMany?
    private List<Long> booksInCart;

    public ShoppingCart(){
        booksInCart = new ArrayList<>();
    }
//    public ShoppingCart(List<Long> booksInCart) {
//        this.booksInCart = booksInCart;
//    }

    public Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public List<Long> getBooksInCart() {
        return booksInCart;
    }

    public void setBooksInCart(List<Long> booksInCart) {
        this.booksInCart = booksInCart;
    }
}
