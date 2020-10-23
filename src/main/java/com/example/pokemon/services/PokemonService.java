package com.example.pokemon.services;

import com.example.pokemon.entities.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private PokemonConsumerService pokemonConsumerService;

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
                var pokemon = new Pokemon(pokemonDto.getName(), pokemonDto.getWeight(), pokemonDto.getHeight());
                pokemons.add(this.save(pokemon));
            }
        }
        return pokemons;
    }

    @CachePut(value = "pokemonCache", key = "#result.id")
    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }
}
