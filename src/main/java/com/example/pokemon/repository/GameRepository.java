package com.example.pokemon.repository;

import com.example.pokemon.entities.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    @Query("{ 'name' : ?0 }")
    Game findByName(String name);

    @Query("{'name': {$regex: ?0 }}")
    List<Game> findByNameRegex(String name);
}
