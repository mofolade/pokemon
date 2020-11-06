package com.example.pokemon.repository;

import com.example.pokemon.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query("{'name': {$regex: ?0 }}")
    List<User> findByNameRegex(String name);

    @Query("{'name': {$regex: ?0 }, 'city' : {$regex: ?1 } , 'username' : {$regex: ?2 } , 'reg_year' : ?3 }")
    List<User> findByAttributes(String name,String city,String username,String reg_year);
}
