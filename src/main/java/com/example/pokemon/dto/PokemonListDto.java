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
    private List<PokemonBaseDto> pokemonsNameUrl;

    public PokemonListDto() {
    }

    public PokemonListDto(List<PokemonBaseDto> pokemonsNameUrl) {
        this.pokemonsNameUrl = pokemonsNameUrl;
    }

    public PokemonListDto(String nextUrl, List<PokemonBaseDto> pokemonsNameUrl) {
        this.nextUrl = nextUrl;
        this.pokemonsNameUrl = pokemonsNameUrl;
    }

    public List<PokemonBaseDto> getPokemon() {
        return pokemonsNameUrl;
    }

    public void setPokemon(List<PokemonBaseDto> pokemonsNameUrl) {
        this.pokemonsNameUrl = pokemonsNameUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
}
