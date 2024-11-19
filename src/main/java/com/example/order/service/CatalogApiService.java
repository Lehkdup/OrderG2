package com.example.order.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CatalogApiService {
    private final WebClient webClient;

    public CatalogApiService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> callCatalogApi(String id){
        return webClient.get()
                .uri("/books/{id}")
                .retrieve()
                .bodyToMono(String.class);
    }

}
