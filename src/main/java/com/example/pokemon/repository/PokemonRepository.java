package com.example.pokemon.repository;

import com.example.pokemon.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository  extends MongoRepository<Pokemon, String> {

    @Query("{ 'name' : ?0 }")
    Pokemon findByName(String name);

    @Query("{'name': {$regex: ?0 }}")
    List<Pokemon> findByNameRegex(String name);

    @Query("{'name': {$regex: ?0 }, 'base_experience' : ?1 , 'height' : ?2 , 'weight' : ?3 }")
    List<Pokemon> findByAttributes(String name,int baseexperience,int height,int weight);

}
