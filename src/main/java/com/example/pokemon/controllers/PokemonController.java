package com.example.pokemon.controllers;

import com.example.pokemon.dto.PokemonBaseDto;
import com.example.pokemon.dto.PokemonListDto;
import com.example.pokemon.entities.Pokemon;
import com.example.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> findPokemons(@RequestParam String name) {
        var pokemon = pokemonService.findAll(name);
        return ResponseEntity.ok(pokemon);
    }

    public ResponseEntity<List<Pokemon>> findPokemonsByName(String name) {
        var pokemon = pokemonService.findAll(name);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/list")
    public ArrayList<String> getPokemonList(@RequestParam int offset) {
        ArrayList<String> pokemonList = pokemonService.getList(offset);
        if(pokemonList != null){
            for (String onepokemon : pokemonList) {
                this.findPokemonsByName(onepokemon);
            }
        }
        return pokemonList;
    }

    //{name=espeon, url=https://pokeapi.co/api/v2/pokemon/196/}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable String id) {
        pokemonService.delete(id);
    }

}
