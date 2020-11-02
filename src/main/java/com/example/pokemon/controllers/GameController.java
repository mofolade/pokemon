package com.example.pokemon.controllers;

import com.example.pokemon.entities.Game;
import com.example.pokemon.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Game>> findGames(@RequestParam String name) {
        var game = gameService.findAll(name);
        return ResponseEntity.ok(game);
    }

    public void findGamesByName(String name) {
        var game = gameService.findAll(name);
        ResponseEntity.ok(game);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void updateGame(@PathVariable String id, @RequestBody Game game) {
        gameService.update(id, game);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void deleteGame(@RequestParam(name = "id", required = false)  String id,
                              @RequestParam(name = "name", required = false)  String name) {
        if(id!= null) {
            gameService.deleteById(id);
        }
        else if (name != null){
            gameService.deleteByName(name);
        }
    }

    @DeleteMapping("/all/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void deleteAllGames() {
        gameService.deleteAll();
    }

}
