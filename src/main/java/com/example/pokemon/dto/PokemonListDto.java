package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

public class PokemonListDto {

    @JsonProperty("next")
    private String nextUrl;

    @JsonProperty("results")
    @JsonDeserialize(as=ArrayList.class, contentAs= PokemonBaseDto.class)
    private List<PokemonBaseDto> modelsTwo;

    public PokemonListDto() {
    }

    public PokemonListDto(List<PokemonBaseDto> modelsTwo) {
        this.modelsTwo = modelsTwo;
    }

    public PokemonListDto(String nextUrl, List<PokemonBaseDto> modelsTwo) {
        this.nextUrl = nextUrl;
        this.modelsTwo = modelsTwo;
    }

    public List<PokemonBaseDto> getPokemon() {
        return modelsTwo;
    }

    public void setPokemon(List<PokemonBaseDto> modelsTwo) {
        this.modelsTwo = modelsTwo;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
}
