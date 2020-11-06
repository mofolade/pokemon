package com.example.pokemon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
public class User {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String username;
    @Size(min = 8, max = 12)
    private String password;
    @NotNull
    private String reg_year;
    private List<String> roles;

    public User() {

    }

    public User(String name, String city, String username, String password, String reg_year, List<String> roles) {
        this.name = name;
        this.city = city;
        this.username = username;
        this.password = password;
        this.reg_year = reg_year;
        this.roles = roles;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getReg_year() {
        return reg_year;
    }

    public void setReg_year(String reg_year) {
        this.reg_year = reg_year;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

