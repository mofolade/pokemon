package com.example.pokemon.services;

import com.example.pokemon.dto.PokemonBaseDto;
import com.example.pokemon.dto.PokemonListDto;
import com.example.pokemon.entities.Pokemon;
import com.example.pokemon.mappers.PokemonMappers;
import com.example.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private PokemonConsumerService pokemonConsumerService;
    @Autowired
    private PokemonMappers pokemonMappers;

    @Cacheable(value = "pokemonCache", key = "#name")
    public List<Pokemon> findAll(String name) {
        var pokemons = pokemonRepository.findAll();
        System.out.println("Fresh data...");
        pokemons = pokemons.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if(pokemons.isEmpty()){
            //System.out.println("new pokemon");
            var pokemonDto = pokemonConsumerService.search(name);
            if(pokemonDto != null){
                /*var pokemon = new Pokemon(pokemonDto.getName(), pokemonDto.getBase_experience(),
                        pokemonDto.getHeight(),pokemonDto.getWeight(), pokemonDto.getGame_indices(),
                        pokemonDto.getAbilities(),pokemonDto.getLocation_area_encounters(),
                        pokemonDto.getTypes());*/
                var pokemon = pokemonMappers.pokemonDtoToPokemon(pokemonDto);
                pokemons.add(this.save(pokemon));
            }
        }
        return pokemons;
    }

    @Cacheable(value = "pokemonCache")
    public List<Pokemon> findByPokemonNameRegex(String name) {
        var pokemons = pokemonRepository.findByNameRegex(name);
        return pokemons;
    }

    @Cacheable(value = "pokemonCache")
    public List<Pokemon> findByPokemonAttributes(String name,int baseexperience,int height,int weight) {
        var pokemons = pokemonRepository.findByAttributes(name,baseexperience,height,weight);
        return pokemons;
    }

    public ArrayList<String> getList(int offset) {
        PokemonListDto pokemonList  = pokemonConsumerService.pokemonList(offset);
        ArrayList<String> pokemonsArray = new ArrayList<String>();
        if(pokemonList != null){
            List<PokemonBaseDto> pokemons = pokemonList.getPokemon();

            for (PokemonBaseDto onepokemon : pokemons) {
                String name= onepokemon.getName();
                pokemonsArray.add(name);
            }
        }

        return pokemonsArray;
    }

    @CachePut(value = "pokemonCache", key = "#result.id")
    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @CachePut(value = "pokemonCache", key = "#id")
    public void update(String id, Pokemon pokemon) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemon");
        }
        pokemon.setId(id);
        pokemonRepository.save(pokemon);
    }

    @CacheEvict(value = "pokemonCache", allEntries = true)
    public void deleteById(String id) {
        if(!pokemonRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemon");
        }
        pokemonRepository.deleteById(id);
        System.out.println("Deleted");
    }

    @CacheEvict(value = "pokemonCache", allEntries = true)
    public void deleteByName(String name) {
        Pokemon pokemon = pokemonRepository.findByName(name);
        if(!pokemonRepository.existsById(pokemon.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta pokemon");
        }
        pokemonRepository.deleteById(pokemon.getId());
        System.out.println("Deleted");
    }

    @CacheEvict(value = "pokemonCache", allEntries = true)
    public void deleteAll() {
        pokemonRepository.deleteAll();
    }

    public List<Pokemon> getAllPokemon(){
        var pokemons = pokemonRepository.findAll();
        return pokemons;
    }
}
