package com.example.pokemon.services;

import com.example.pokemon.dto.PokemonDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ConfigurationProperties(value = "example.pokemon",ignoreUnknownFields = false)
public class PokemonConsumerService {
    private final RestTemplate restTemplate;
    //private String url;

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonDto search(String name){
        var urlWithTitleQuery = "https://pokeapi.co/api/v2/pokemon/" + name;
        System.out.println(urlWithTitleQuery);
        var pokemon = restTemplate.getForObject(urlWithTitleQuery, PokemonDto.class);

        if(pokemon == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found.");
        }
        return pokemon;

    }

}
