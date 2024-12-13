package com.example.order.controller;

import com.example.order.entity.Book;
import com.example.order.entity.ShoppingCart;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final ShoppingCart cart;
    private List<Book> tempBooksInCart = new ArrayList<>();
    @Value("${catalog.url}") String url;

    public BookController(RestClient.Builder restClientBuilder, ShoppingCart cart) {
        this.restClient = restClientBuilder.build();
        this.cart = cart;
    }

    public Book[] getBook(String keywords) {
        String requestUrl; // Lokale Variable für die dynamische URL

        if (keywords == null || keywords.isEmpty()) {
            requestUrl = url + "/books";
            logger.info("Aufruf der URL: {}", requestUrl);
        } else {
            requestUrl = url + "/books/search/" + keywords;
            logger.info("Aufruf der URL mit Schlüsselwörtern: {}", requestUrl);
        }

        try {
            return restClient.get()
                    .uri(requestUrl)
                    .retrieve()
                    .body(Book[].class);
        } catch (Exception e) {
            logger.error("Fehler beim Abrufen der Bücher von {}: {}", requestUrl, e.getMessage());
            return new Book[0];
        }
    }



    @GetMapping("/")
    public String catalog() {
        return "index";
    }
    /*
    @GetMapping("/search")
    public String SearchBook(Model model, String keywords) {
        model.addAttribute("books", getBook(keywords));
        model.addAttribute("keywords", keywords);
        return "index";
    }*/
    @GetMapping("/search")
    public String SearchBook(Model model, String keywords) {

        Book[] books = getBook(keywords);
        for(Book book : books){
            tempBooksInCart.add(book);
        }

        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping(path = "/cart/add/{id}")
    public String addToCart(@PathVariable("id") String ISBN, Model model) {
        Optional<Book> result = tempBooksInCart.stream()
                .filter(book -> book.getISBN().equals(ISBN))
                .findFirst();

        if (result.isPresent()) {
            Book foundBook = result.get();
            cart.addBook(foundBook);
        } else {
            System.out.println("Kein Buch mit der ISBN " + ISBN + " gefunden.");
        }

        return "redirect:/";
    }


    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("books", tempBooksInCart);
        model.addAttribute("cartSize", cart.getBooksInCart().size());
        return "catalog";
    }

    @GetMapping("/shoppingcart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cart);
        return "shoppingcart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") String ISBN, Model model) {
        Optional<Book> result = cart.getBooksInCart().stream()
                .filter(book -> book.getISBN().equals(ISBN))
                .findFirst();

        if (result.isPresent()) {
            Book foundBook = result.get();
            cart.removeBook(foundBook);
        } else {
            System.out.println("Kein Buch mit der ISBN " + ISBN + " im Warenkorb gefunden.");
        }

        return "redirect:/shoppingcart";
    }

    /*
    private Book[] listAllBook() {
        return restClient
                .get()
                .uri("http://localhost:8080/Book")
                .retrieve()
                .body(Book[].class);
    }
    */

}


