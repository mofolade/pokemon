package com.example.pokemon.services;

import com.example.pokemon.entities.Game;
import com.example.pokemon.mappers.GameMappers;
import com.example.pokemon.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameConsumerService gameConsumerService;
    @Autowired
    private GameMappers gameMappers;

    public List<Game> findAll(String name) {
        var games = gameRepository.findAll();
        System.out.println("Fresh data...");
        games = games.stream()
                .filter(game -> game.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if(games.isEmpty()){
            var gameDto = gameConsumerService.search(name);
            if(gameDto != null){
                var game = gameMappers.gameDtoToGame(gameDto);
                games.add(this.save(game));
            }
        }
        return games;
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public void update(String id, Game game) {
        if(!gameRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta game");
        }
        game.setId(id);
        gameRepository.save(game);
    }

    public void deleteById(String id) {
        if(!gameRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta game");
        }
        gameRepository.deleteById(id);
        System.out.println("Deleted");
    }

    public void deleteByName(String name) {
        Game game = gameRepository.findByName(name);
        if(!gameRepository.existsById(game.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta game");
        }
        gameRepository.deleteById(game.getId());
        System.out.println("Deleted");
    }

    public void deleteAll() {
        gameRepository.deleteAll();
    }

    public List<Game> getAllGame(){
        var games = gameRepository.findAll();
        return games;
    }
}
