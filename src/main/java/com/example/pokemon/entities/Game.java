package com.example.pokemon.entities;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Builder
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private List<Object> names;
    private List<Object> moves;
    private List<Object> pokemon_species;

    public Game() {
    }

    public Game(String id, String name, List<Object> names, List<Object> moves, List<Object> pokemon_species) {
        this.id = id;
        this.name = name;
        this.names = names;
        this.moves = moves;
        this.pokemon_species = pokemon_species;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
