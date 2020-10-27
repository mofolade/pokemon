package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AbilityDto {

    private List<Object> ability;
    private boolean is_hidden;
    private int slot;

    @JsonCreator
    public AbilityDto(
            @JsonProperty("ability") List<Object> ability,
            @JsonProperty("is_hidden") boolean is_hidden,
            @JsonProperty("slot")int slot) {
        //this.ability = ability;
        this.is_hidden = is_hidden;
        this.slot = slot;
    }

    public AbilityDto() {
    }

    public List<Object> getAbility() {
        return ability;
    }

    public void setAbility(List<Object> ability) {
        this.ability = ability;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "AbilityDto{" +
                "ability='" + ability + '\'' +
                ", is_hidden=" + is_hidden +
                ", slot=" + slot +
                '}';
    }
}
