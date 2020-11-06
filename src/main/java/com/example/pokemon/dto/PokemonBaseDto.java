package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonBaseDto {

    private String name;
    private String url;

    @JsonCreator
    public PokemonBaseDto(
            @JsonProperty("name") String name,
            @JsonProperty("url") String url) {
        this.name = name;
        this.url = url;
    }

    public PokemonBaseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
