package com.monnos.database;

import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/*
  Definir os dados a serem persistidos
          • ID: auto-increment (think about)
          • Nome : string
          • Clima : string
          • Terreno : string
          • Para cada planeta também devemos ter a quantidade de aparições em filmes que
          deve ser obtida pela api do Star Wars na inserção do planeta : Int
   */

@Document()
public class Planet {

    @Id
    private String id; //id de cada planeta
    @Indexed(unique = true)
    private String name; //nome do planeta
    private String climate; //clima do planeta
    private String terrain; //terreno do planeta
    private int films; //total de aparicoes em filmes

    public Planet(String name, String climate, String terrain, Integer films) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.films = films;
    }

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.films = 0;
    }

    public Planet(){
    }

    public String getId() {
        return id;
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

    public Integer getFilms() {
        return films;
    }

    public void setFilms(Integer films) {
        this.films = films;
    }
}

