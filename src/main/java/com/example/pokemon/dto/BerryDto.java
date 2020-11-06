package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BerryDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("growth_time")
    private int growth_time;
    @JsonProperty("max_harvest")
    private int max_harvest;
    @JsonProperty("natural_gift_power")
    private int natural_gift_power;
    @JsonProperty("size")
    private int size;
    @JsonProperty("smoothness")
    private int smoothness;
    @JsonProperty("soil_dryness")
    private int soil_dryness;
    @JsonProperty("flavors")
    private List<Object> flavors;

    public BerryDto() {
    }

    public BerryDto(String name, int growth_time, int max_harvest, int natural_gift_power, int size, int smoothness, int soil_dryness, List<Object> flavors) {
        this.name = name;
        this.growth_time = growth_time;
        this.max_harvest = max_harvest;
        this.natural_gift_power = natural_gift_power;
        this.size = size;
        this.smoothness = smoothness;
        this.soil_dryness = soil_dryness;
        this.flavors = flavors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrowth_time() {
        return growth_time;
    }

    public void setGrowth_time(int growth_time) {
        this.growth_time = growth_time;
    }

    public int getMax_harvest() {
        return max_harvest;
    }

    public void setMax_harvest(int max_harvest) {
        this.max_harvest = max_harvest;
    }

    public int getNatural_gift_power() {
        return natural_gift_power;
    }

    public void setNatural_gift_power(int natural_gift_power) {
        this.natural_gift_power = natural_gift_power;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSmoothness() {
        return smoothness;
    }

    public void setSmoothness(int smoothness) {
        this.smoothness = smoothness;
    }

    public int getSoil_dryness() {
        return soil_dryness;
    }

    public void setSoil_dryness(int soil_dryness) {
        this.soil_dryness = soil_dryness;
    }

    public List<Object> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<Object> flavors) {
        this.flavors = flavors;
    }

}
