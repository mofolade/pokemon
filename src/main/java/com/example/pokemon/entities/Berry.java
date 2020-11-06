package com.example.pokemon.entities;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Builder
public class Berry implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private int growth_time;
    private int max_harvest;
    private int natural_gift_power;
    private int size;
    private int smoothness;
    private int soil_dryness;
    private List<Object> flavors;

    public Berry() {
    }

    public Berry(String id, String name, int growth_time, int max_harvest, int natural_gift_power, int size, int smoothness, int soil_dryness, List<Object> flavors) {
        this.id = id;
        this.name = name;
        this.growth_time = growth_time;
        this.max_harvest = max_harvest;
        this.natural_gift_power = natural_gift_power;
        this.size = size;
        this.smoothness = smoothness;
        this.soil_dryness = soil_dryness;
        this.flavors = flavors;
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
