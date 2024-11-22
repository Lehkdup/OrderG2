package com.example.order.entity;

import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Long> booksInCart;

    public ShoppingCart(){
        booksInCart = new ArrayList<>();
    }

    public List<Long> getBooksInCart() {
        return booksInCart;
    }

    public void setBooksInCart(List<Long> booksInCart) {
        this.booksInCart = booksInCart;
    }

    public void addBook(Long bookId){
        this.booksInCart.add(bookId);
    }
}
