package com.example.pokemon.repository;

import com.example.pokemon.entities.Berry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BerryRepository extends MongoRepository<Berry, String> {
    @Query("{ 'name' : ?0 }")
    Berry findByName(String name);

    @Query("{'name': {$regex: ?0 }}")
    List<Berry> findByNameRegex(String name);

    @Query("{'name': {$regex: ?0 }, 'max_harvest' : ?1 , 'size' : ?2 , 'growth_time' : ?3 }")
    List<Berry> findByAttributes(String name,int max_harvest,int size,int growth_time);
}
