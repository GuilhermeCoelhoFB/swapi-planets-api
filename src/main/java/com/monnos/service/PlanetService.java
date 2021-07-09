package com.monnos.service;

import com.monnos.exception.ApiRequestException;
import com.monnos.model.Planet;
import com.monnos.model.swapiPlanet.SwapiPlanet;
import com.monnos.model.swapiPlanet.SwapiPlanetList;
import com.monnos.repository.PlanetRepo;
import com.monnos.repository.SwapiPlanetRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;

@Service
public class PlanetService {
    private final PlanetRepo planetRepo;
    private final SwapiPlanetRepo swapiPlanetRepo;

    @Autowired
    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;


    public List<Planet> getAllPlanets() throws ApiRequestException {
        try{
            if (planetRepo.findAll()!=null)
                  return planetRepo.findAll();
            else throw new ApiRequestException("Failed");
        } catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public Optional<Planet> findByName(String name) throws ApiRequestException {
        try {
            if (planetRepo.findPlanetByName(name).isPresent())
                return planetRepo.findPlanetByName(name);

            else throw new ApiRequestException("Oops, planet not found");
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public Optional<Planet> findById(String id) throws ApiRequestException {

        try {
            Optional<Planet> planet = planetRepo.findById(id);
            if (planet.isPresent()){
                return planetRepo.findById(id);
            }
            else {
                throw new ApiRequestException("Oops, planet not found");
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void deleteById(String id) throws ApiRequestException {
        try {
            Optional<Planet> opPlanet = planetRepo.findById(id);
            if (opPlanet.isPresent()) {
                planetRepo.deleteById(id);
            } else {
                throw new ApiRequestException("Oops, planet not found");
            }
        } catch(Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void addPlanet(Planet planet) throws ApiRequestException {

        try {
            Optional<Planet> planetOptional = planetRepo.findPlanetByName(planet.getName().toLowerCase(Locale.ROOT));

            if (planetOptional.isPresent()) {
                throw new ApiRequestException("Planet taken");
            } else {
                SwapiPlanetList planetList;
                SwapiPlanet swapiPlanet;
                planetList = this.restTemplate.getForObject(
                        String.format("https://swapi.dev/api/planets/?search=%s", planet.getName().toLowerCase(Locale.ROOT))
                        ,SwapiPlanetList.class);

                if (planetList.getCount() == 1) {
                    swapiPlanet = planetList.getResults().get(0);
                    planet.setAppearances(swapiPlanet.getFilms().size());
                    planetRepo.save(planet);

                } else if (planetList.getCount() != 1) {
                    planet.setAppearances(0);

                    planetRepo.save(planet);

                } else throw new NullPointerException("Invalid ID");
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public SwapiPlanet findOneSwapiPlanet(String id) throws ApiRequestException {
        try{
            if (id!=null) {
                Mono<SwapiPlanet> monoSwapiPlanet = this.webClient
                        .get()
                        .uri("planets/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(SwapiPlanet.class)
                        .timeout(Duration.ofMinutes(1));
                return monoSwapiPlanet.block();
            }
            else{
                throw new ApiRequestException("Oops, invalid ID");
            }
        } catch (Exception e) {
            throw new ApiRequestException("Oops, invalid ID");
        }
    }

    public List<SwapiPlanet> getAllSwapiPlanets() throws ApiRequestException {
        try {
            int page = 1;
            List<SwapiPlanet> swapiAllPlanets = new ArrayList<>();
            Mono<SwapiPlanetList> monoPlanet;

                do {
                    monoPlanet = this.webClient
                            .get()
                            .uri("planets/?page=" + page)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(SwapiPlanetList.class);

                    swapiAllPlanets.addAll(monoPlanet.block().getResults());
                    page++;

                } while (monoPlanet.block().getNext() != null);

                return swapiAllPlanets;

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public PlanetService(PlanetRepo planetRepo, SwapiPlanetRepo swapiPlanetRepo) {
        this.planetRepo = planetRepo;
        this.swapiPlanetRepo = swapiPlanetRepo;
    }

}
