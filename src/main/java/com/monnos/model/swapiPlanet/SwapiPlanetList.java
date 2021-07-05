package com.monnos.model.swapiPlanet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.monnos.model.Planet;

import java.util.List;

public class SwapiPlanetList {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private int count;
    private String next;
    private String previous;
    private List<SwapiPlanet> results;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<SwapiPlanet> getResults() {
        return results;
    }

    public void setResults(List<SwapiPlanet> results) {
        this.results = results;
    }

//    public List<SwapiPlanet> getFilms() {
//        return films;
//    }
//
//    public void setFilms(List<SwapiPlanet> films) {
//        this.films = films;
//    }

    public SwapiPlanetList(int count, String next, String previous, List<SwapiPlanet> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "  next='" + next + '\'' +
                ", previuos='" + previous + '\'' +
                ", count='" + count + '\'' +
                ", results='" + results + '\'' +
                '}';
    }
}
