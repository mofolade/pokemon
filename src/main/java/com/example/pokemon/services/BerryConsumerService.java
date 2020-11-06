package com.example.pokemon.services;

import com.example.pokemon.dto.BerryDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ConfigurationProperties(value = "example.pokemon",ignoreUnknownFields = false)
public class BerryConsumerService {
    private final RestTemplate restTemplate;
    private String url;

    public BerryConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BerryDto search(String name){
        var urlWithTitleQuery = url + "berry/"+ name;

        var berry = restTemplate.getForObject(urlWithTitleQuery, BerryDto.class);

        if(berry == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No game found.");
        }
        return berry;

    }

    public void setUrl(String url) {
        this.url = url;
    }
}
