package com.example.order.controller;

import com.example.order.entity.ShoppingCart;
import com.example.order.repository.ShoppingCartRepository;
import com.example.order.service.CatalogApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private ShoppingCartRepository shoppingCartRepository;
    private final CatalogApiService catalogApiService;

    public ShoppingCartController(ShoppingCartRepository shoppingCartRepository, CatalogApiService catalogApiService){
        this.catalogApiService = catalogApiService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @PostMapping(path ="/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addToCart(@RequestBody Map<String, Long> request){
        Long cartId = request.get("cartId");
        Long bookId = request.get("bookId");
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findById(cartId);
        if(optionalShoppingCart.isPresent()){
            ShoppingCart shoppingCart = optionalShoppingCart.get();
            shoppingCart.getBooksInCart().add(bookId);
            shoppingCartRepository.save(shoppingCart);
            return ResponseEntity.ok(shoppingCart);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cart with this id does not exist");
        }
    }

    @PostMapping(produces = "application/json")
    public ShoppingCart createNewCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @GetMapping(path = "{id}")
    public Optional<ShoppingCart> getCartById(@PathVariable Long id){
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);
        return shoppingCart;
    }
}
