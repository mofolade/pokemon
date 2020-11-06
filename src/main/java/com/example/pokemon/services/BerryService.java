package com.example.pokemon.services;

import com.example.pokemon.entities.Berry;
import com.example.pokemon.mappers.BerryMappers;
import com.example.pokemon.repository.BerryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BerryService {

    @Autowired
    private BerryRepository berryRepository;
    @Autowired
    private BerryConsumerService berryConsumerService;
    @Autowired
    private BerryMappers berryMappers;

    public List<Berry> findAll(String name) {
        var berries = berryRepository.findAll();
        System.out.println("Fresh data...");
        berries = berries.stream()
                .filter(berry -> berry.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if(berries.isEmpty()){
            var berryDto = berryConsumerService.search(name);
            if(berryDto != null){
                var berry = berryMappers.berryDtoToBerry(berryDto);
                berries.add(this.save(berry));
            }
        }
        return berries;
    }

    public List<Berry> findByBerryNameRegex(String name) {
        var berries = berryRepository.findByNameRegex(name);
        return berries;
    }

    public List<Berry> findByBerryAttributes(String name, int max_harvest, int size, int growth_time) {
        var berries = berryRepository.findByAttributes(name,max_harvest,size,growth_time);
        return berries;
    }

    public Berry save(Berry berry) {
        return berryRepository.save(berry);
    }

    public void update(String id, Berry berry) {
        if(!berryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta berry");
        }
        berry.setId(id);
        berryRepository.save(berry);
    }

    public void deleteById(String id) {
        if(!berryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta berry");
        }
        berryRepository.deleteById(id);
        System.out.println("Deleted");
    }

    public void deleteByName(String name) {
        Berry berry = berryRepository.findByName(name);
        if(!berryRepository.existsById(berry.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde ej hitta berry");
        }
        berryRepository.deleteById(berry.getId());
        System.out.println("Deleted");
    }

    public void deleteAll() {
        berryRepository.deleteAll();
    }

    public List<Berry> getAllBerry(){
        var berries = berryRepository.findAll();
        return berries;
    }
}
