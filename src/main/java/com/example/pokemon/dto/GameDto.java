package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GameDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("names")
    private List<Object> names;
    @JsonProperty("moves")
    private List<Object> moves;
    @JsonProperty("pokemon_species")
    private List<Object> pokemon_species;

    public GameDto() {
    }

    public GameDto(String name, List<Object> names, List<Object> moves, List<Object> pokemon_species) {
        this.name = name;
        this.names = names;
        this.moves = moves;
        this.pokemon_species = pokemon_species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getNames() {
        return names;
    }

    public void setNames(List<Object> names) {
        this.names = names;
    }

    public List<Object> getMoves() {
        return moves;
    }

    public void setMoves(List<Object> moves) {
        this.moves = moves;
    }

    public List<Object> getPokemon_species() {
        return pokemon_species;
    }

    public void setPokemon_species(List<Object> pokemon_species) {
        this.pokemon_species = pokemon_species;
    }
}
