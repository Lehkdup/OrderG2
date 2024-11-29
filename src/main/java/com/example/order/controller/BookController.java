package com.example.order.controller;

import com.example.order.entity.Book;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

@Controller
public class BookController {
    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public Book[] getBook(String keywords) {
        String url;
        if (keywords == null || keywords.isEmpty()) {
            url = "http://localhost:8080/books";
            logger.info("Aufruf der URL: {}", url);
        }else{
            url = "http://localhost:8080/books/search/" + keywords;
            logger.info("http://localhost:8080/books/search/" + keywords);
        }

        try {
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(Book[].class);
        } catch (Exception e) {
            logger.error("Fehler beim Abrufen der Bücher von {}: {}", url, e.getMessage());
            return new Book[0];
        }
    }


    @GetMapping("/")
    public String catalog() {
        return "index"; // Name der Datei ohne `.html`, wenn du Thymeleaf nutzt
    }
    /*
    @GetMapping("/search")
    public String SearchBook(Model model, String keywords) {
        model.addAttribute("books", getBook(keywords));
        model.addAttribute("keywords", keywords); // Keywords dem Model hinzufügen
        return "index";
    }*/
    @GetMapping("/search")
    public String SearchBook(Model model, String keywords) {

        model.addAttribute("books", getBook(keywords));
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


