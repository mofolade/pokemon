package com.example.pokemon.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Ability {

    /*@JsonProperty("abilities")
    private List<String> abilities;*/
    private List<String> ability;
   /* private Boolean is_hidden;
    private int slot;*/

    @JsonCreator
    public Ability(
            @JsonProperty("ability") List<String> ability/*,
            @JsonProperty("is_hidden") Boolean is_hidden,
            @JsonProperty("slot") int slot*/) {
        this.ability = ability;
        /*this.is_hidden = is_hidden;
        this.slot = slot;*/
    }

    public Ability() {
    }

    public List<String> getAbility() {
        return ability;
    }

    public void setAbility(List<String> ability) {
        this.ability = ability;
    }

   /* public Boolean getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(Boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }*/
}
