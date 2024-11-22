package com.example.order.controller;

import com.example.order.entity.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @PostMapping(path ="/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addToCart(@RequestBody Map<String, Long> request, HttpSession session){//, @ModelAttribute("shoppingCart") ShoppingCart shoppingCart){
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }

        Long bookId = request.get("bookId");
        shoppingCart.addBook(bookId);
        return ResponseEntity.ok(shoppingCart);
    }

    @GetMapping
    public ResponseEntity getCart(HttpSession session){//@ModelAttribute("shoppingCart") ShoppingCart shoppingCart){
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }
        return ResponseEntity.ok(shoppingCart);
    }

}
