package com.example.pokemon.services;

import com.example.pokemon.dto.GameDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ConfigurationProperties(value = "example.pokemon",ignoreUnknownFields = false)
public class GameConsumerService {
    private final RestTemplate restTemplate;
    private String url;

    public GameConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public GameDto search(String name){
        var urlWithTitleQuery = url + "generation/"+ name;

        var game = restTemplate.getForObject(urlWithTitleQuery, GameDto.class);

        if(game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No game found.");
        }
        return game;

    }

    public void setUrl(String url) {
        this.url = url;
    }
}
