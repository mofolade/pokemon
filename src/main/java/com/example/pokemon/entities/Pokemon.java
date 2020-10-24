package com.example.pokemon.entities;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private int base_experience;
    private int height;
    private int weight;
    private List<Object> game_indices;
    private List<Object> abilities;
    private String location_area_encounters;
    private List<Object> types;

    public Pokemon() {
    }

    public Pokemon(String name) {
        this.name = name;
    }

    public Pokemon(String name, int base_experience, int height, int weight, List<Object> game_indices,
                   List<Object> abilities, String location_area_encounters, List<Object> types) {
        this.name = name;
        this.base_experience = base_experience;
        this.height = height;
        this.weight = weight;
        this.game_indices = game_indices;
        this.abilities = abilities;
        this.location_area_encounters = location_area_encounters;
        this.types = types;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Object> getGame_indices() {
        return game_indices;
    }

    public void setGame_indices(List<Object> game_indices) {
        this.game_indices = game_indices;
    }

    public List<Object> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Object> abilities) {
        this.abilities = abilities;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public List<Object> getTypes() {
        return types;
    }

    public void setTypes(List<Object> types) {
        this.types = types;
    }
}
