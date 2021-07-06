package com.monnos.controller;

import com.monnos.exception.ApiRequestException;
import com.monnos.model.Planet;
import com.monnos.model.swapiPlanet.SwapiPlanet;
import com.monnos.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//rotas utilizadas para funcionamento do codigo

    /*
        • Adicionar um planeta (com nome, clima e terreno) /api/planets/add {JSON}
        • Listar planetas do banco de dados /api/planets /api/planets/all
        • Listar planetas da API do Star Wars /api/planets/allSwapi
        • Buscar por nome no banco de dados /api/planets/byName/{name}
        • Buscar por ID no banco de dados /api/planets/id/{id}
        • Remover planeta   /api/planets/delete/{id}
     */

@RestController
@RequestMapping("api/planets")
public class PlanetController {

    private final PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Planet>> fetchAllPlanets() throws ApiRequestException {
        return new ResponseEntity<>( planetService.getAllPlanets(), HttpStatus.OK);
    }

    @GetMapping("/idSwapi/{id}")
    public ResponseEntity<?> findOneFromSwapi(@PathVariable("id") String id) throws ApiRequestException {
        return new ResponseEntity<>(
                planetService.findOneSwapiPlanet(id), HttpStatus.OK);
    }
    @GetMapping("/allSwapi")
    public ResponseEntity<List<SwapiPlanet>> fetchAllSwapiPlanets() throws ApiRequestException {
        return new ResponseEntity<>(
                planetService.getAllSwapiPlanets(), HttpStatus.OK);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Optional<Planet>> findByName(@PathVariable("name") String name) throws ApiRequestException {
        return new ResponseEntity<>(planetService.findByName(name), HttpStatus.FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Planet>> findById(@PathVariable("id") String id) throws ApiRequestException {
        return new ResponseEntity<>(planetService.findById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws ApiRequestException {

        planetService.deleteById(planetService.findById(id).get().getId());
        return new ResponseEntity("Planet deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPlanet(@RequestBody Planet planet) throws ApiRequestException {

        planetService.addPlanet(planet);
        return new ResponseEntity<>("Planet Successfully added", HttpStatus.CREATED);
    }

}
