package com.example.pokemon.controllers;

import com.example.pokemon.entities.Pokemon;
import com.example.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
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

    @GetMapping("/search")
    public ResponseEntity<List<Pokemon>> findByName(@RequestParam String name) {
        var pokemon = pokemonService.findByUserNameRegex(name);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Pokemon>> findByAttributes(@RequestParam(name = "name") String name,
                                                          @RequestParam(name = "baseexperience")  int baseexperience,
                                                          @RequestParam(name = "height")  int height,
                                                          @RequestParam(name = "weight")  int weight) {
        var pokemon = pokemonService.findByUserAttributes(name,baseexperience,height,weight);
        return ResponseEntity.ok(pokemon);
    }

    public void findPokemonsByName(String name) {
        var pokemon = pokemonService.findAll(name);
        ResponseEntity.ok(pokemon);
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

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@RequestParam(name = "id", required = false)  String id,
                              @RequestParam(name = "name", required = false)  String name) {
        if(id!= null) {
            pokemonService.deleteById(id);
        }
        else if (name != null){
            pokemonService.deleteByName(name);
        }
    }

    @DeleteMapping("/all/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPokemon() {
        pokemonService.deleteAll();
    }

    @RequestMapping(value = "/index")
    public String pokemons(Model model){
        List<Pokemon> pokemons =  pokemonService.getAllPokemon();
        model.addAttribute("pokemons",pokemons);
        return "index";
    }

}
