package com.example.pokemon.repository;

import com.example.pokemon.entities.Berry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BerryRepository extends MongoRepository<Berry, String> {
    @Query("{ 'name' : ?0 }")
    Berry findByName(String name);
}
