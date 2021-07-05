package com.monnos.model.swapiPlanet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPlanet{

    private String name;
    private String climate;
    private String terrain;
    private List<String> films;

    public SwapiPlanet( String name, String climate, String terrain, List<String> films) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.films = films;
    }

    public SwapiPlanet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "  name='" + name + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", films='" + films + '\'' +
                '}';
    }
}
