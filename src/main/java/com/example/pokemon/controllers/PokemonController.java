package com.example.pokemon.controllers;

import com.example.pokemon.entities.Pokemon;
import com.example.pokemon.services.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    /*@GetMapping
    @Secured("ROLE_USER")
    public ResponseEntity<List<Pokemon>> findAll() {
        var pokemons = pokemonService.findPokemons();
        return ResponseEntity.ok(pokemons);
    }*/

    @Operation(summary = "Find pokemons with a name.")
    @GetMapping
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content)
    })
    public ResponseEntity<List<Pokemon>> findPokemons(@RequestParam String name) {
        var pokemon = pokemonService.findAll(name);
        return ResponseEntity.ok(pokemon);
    }

    @Operation(summary = "Pokemon search by name detail.")
    @GetMapping("/search")
    public ResponseEntity<List<Pokemon>> findByName(@RequestParam String name) {
        var pokemon = pokemonService.findByPokemonNameRegex(name);
        return ResponseEntity.ok(pokemon);
    }

    @Operation(summary = "Pokemon search by name detail, baseexperience, height, weight. /api/v1/pokemons/filter?name=it&baseexperience=159&height=15&weight=498")
    @GetMapping("/filter")
    public ResponseEntity<List<Pokemon>> findByAttributes(@RequestParam(name = "name") String name,
                                                          @RequestParam(name = "baseexperience")  int baseexperience,
                                                          @RequestParam(name = "height")  int height,
                                                          @RequestParam(name = "weight")  int weight) {
        var pokemon = pokemonService.findByPokemonAttributes(name,baseexperience,height,weight);
        return ResponseEntity.ok(pokemon);
    }

    @Operation(summary = "Pokemon search by name.")
    public void findPokemonsByName(String name) {
        var pokemon = pokemonService.findAll(name);
        ResponseEntity.ok(pokemon);
    }

    @Operation(summary = "Save pokemons. 100 pokemons withpagination. localhost:8080/api/v1/pokemons/list?offset=100 next:" +
            " localhost:8080/api/v1/pokemons/list?offset=200")
    @GetMapping("/list")
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content)
    })
    public ArrayList<String> getPokemonList(@RequestParam int offset) {
        ArrayList<String> pokemonList = pokemonService.getList(offset);
        if(pokemonList != null){
            for (String onepokemon : pokemonList) {
                this.findPokemonsByName(onepokemon);
            }
        }
        return pokemonList;
    }

    @Operation(summary = "Pokemon search by id.")
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @Operation(summary = "Pokemon delete by id or name.")
    @DeleteMapping
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content)
    })
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

    @Operation(summary = "Delete all pokemons.")
    @DeleteMapping("/all/")
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted pokemon.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Couldn't find pokemon.", content = @Content)
    })
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
