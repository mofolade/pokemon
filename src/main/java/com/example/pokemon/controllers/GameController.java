package com.example.pokemon.controllers;

import com.example.pokemon.entities.Game;
import com.example.pokemon.services.GameService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Operation(summary = "Game search by name.")
    @GetMapping
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Couldn't find game.", content = @Content)
    })
    public ResponseEntity<List<Game>> findGames(@RequestParam String name) {
        var game = gameService.findAll(name);
        return ResponseEntity.ok(game);
    }

    /*@Operation(summary = "Game search by name.")
    public void findGamesByName(String name) {
        var game = gameService.findAll(name);
        ResponseEntity.ok(game);
    }*/

    @Operation(summary = "Game search by name detail.")
    @GetMapping("/search")
    public ResponseEntity<List<Game>> findByName(@RequestParam String name) {
        var game = gameService.findByGameNameRegex(name);
        return ResponseEntity.ok(game);
    }

    @Operation(summary = "Game search by id.")
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@PathVariable String id, @RequestBody Game game) {
        gameService.update(id, game);
    }

    @Operation(summary = "Game delete by id or name.")
    @DeleteMapping
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted game.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Couldn't find game.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@RequestParam(name = "id", required = false)  String id,
                              @RequestParam(name = "name", required = false)  String name) {
        if(id!= null) {
            gameService.deleteById(id);
        }
        else if (name != null){
            gameService.deleteByName(name);
        }
    }

    @Operation(summary = "Delete all games.")
    @DeleteMapping("/all/")
    @Secured("ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted game.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Admin authentication is required.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Couldn't find game.", content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllGames() {
        gameService.deleteAll();
    }

}
