package com.ame.desafio.starwars.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlanetaDTO {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Long id;

    private String name;
    @JsonIgnore
    private String rotation_period;
    @JsonIgnore
    private String orbital_period;
    @JsonIgnore
    private String diameter;

    private String climate;
    @JsonIgnore
    private String gravity;

    private String terrain;
    @JsonIgnore
    private String surface_water;
    @JsonIgnore
    private String population;
    @JsonIgnore
    private List<String> residents;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> films;
    @JsonIgnore
    private String created;
    @JsonIgnore
    private String edited;
    @JsonIgnore
    private String url;

    private int quantity_movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRotation_period() {
        return rotation_period;
    }

    public void setRotation_period(String rotation_period) {
        this.rotation_period = rotation_period;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public void setOrbital_period(String orbital_period) {
        this.orbital_period = orbital_period;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public void setSurface_water(String surface_water) {
        this.surface_water = surface_water;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQuantity_movies() {
        return quantity_movies;
    }

    public void setQuantity_movies(int quantity_movies) {
        this.quantity_movies = quantity_movies;
    }
}
