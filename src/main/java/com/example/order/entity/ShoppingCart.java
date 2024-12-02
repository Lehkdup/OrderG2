package com.example.order.entity;

import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class ShoppingCart {
    private List<Book> booksInCart = new ArrayList<>();

    public ShoppingCart(){
        booksInCart = new ArrayList<>();
    }

    public List<Book> getBooksInCart() {
        return booksInCart;
    }

    public void setBooksInCart(List<Book> booksInCart) {
        this.booksInCart = booksInCart;
    }

    public void addBook(Book book){
        this.booksInCart.add(book);
    }

    public void removeBook(Book book){
        this.booksInCart.remove(book);
    }
}
