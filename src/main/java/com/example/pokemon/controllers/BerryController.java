package com.example.pokemon.controllers;

import com.example.pokemon.entities.Berry;
import com.example.pokemon.services.BerryService;
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
@RequestMapping("/api/v1/berry")
public class BerryController {
    @Autowired
    private BerryService berryService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Berry>> findBerries(@RequestParam String name) {
        var berry = berryService.findAll(name);
        return ResponseEntity.ok(berry);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Berry>> findByName(@RequestParam String name) {
        var berry = berryService.findByBerryNameRegex(name);
        return ResponseEntity.ok(berry);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Berry>> findByAttributes(@RequestParam(name = "name") String name,
                                                        @RequestParam(name = "max_harvest")  int max_harvest,
                                                        @RequestParam(name = "size")  int size,
                                                        @RequestParam(name = "growth_time")  int growth_time) {
        var berry = berryService.findByBerryAttributes(name,max_harvest,size,growth_time);
        return ResponseEntity.ok(berry);
    }

    public void findBerriesByName(String name) {
        var berry = berryService.findAll(name);
        ResponseEntity.ok(berry);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void updateBerry(@PathVariable String id, @RequestBody Berry berry) {
        berryService.update(id, berry);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void deleteBerry(@RequestParam(name = "id", required = false)  String id,
                           @RequestParam(name = "name", required = false)  String name) {
        if(id!= null) {
            berryService.deleteById(id);
        }
        else if (name != null){
            berryService.deleteByName(name);
        }
    }

    @DeleteMapping("/all/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void deleteAllBerries() {
        berryService.deleteAll();
    }
}
