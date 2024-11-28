package com.example.order.controller;

import com.example.order.entity.Book;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

@Controller
public class BookController {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private Book[] getBook(String keywords) {
        String url = "http://localhost:8080/books/search/" + keywords;
        try {
            return restTemplate.getForObject(url, Book[].class);
        } catch (Exception e) {
            logger.error("Fehler beim Abrufen der Bücher von {}: {}", url, e.getMessage());
            return new Book[0];
        }
    }

    @GetMapping("/search")
    public String SearchBook(Model model, @RequestParam String keywords) {
        model.addAttribute("books", getBook(keywords));
        model.addAttribute("keywords", keywords); // Keywords dem Model hinzufügen
        return "index";
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


