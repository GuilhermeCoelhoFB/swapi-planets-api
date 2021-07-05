package com.monnos.service;

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

import java.util.*;

@Service
public class PlanetService {
    private final PlanetRepo planetRepo;
    private final SwapiPlanetRepo swapiPlanetRepo;

    @Autowired
    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    ///////////////methods

    public List<Planet> getAllPlanets(){
        return planetRepo.findAll();
    }

    public Optional<Planet> findByName(String name){
        return planetRepo.findByName(name);
    }

    public Optional<Planet> findById(String id){
        return planetRepo.findById(id);
    }

    public void deleteById(String id){
        Optional<Planet> opPlanet = planetRepo.findById(id);
        if (opPlanet.isPresent()){
            planetRepo.deleteById(id);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public void addPlanet(Planet planet){

        Optional<Planet> planetOptional = planetRepo.findByName(planet.getName().toLowerCase(Locale.ROOT));

        if (planetOptional.isPresent()) {
            throw new IllegalStateException("Planet taken");
        } else {
            SwapiPlanetList planetList;
            SwapiPlanet swapiPlanet;
            planetList = this.restTemplate.getForObject(
                    String.format("https://swapi.dev/api/planets/?search=%s", planet.getName().toLowerCase(Locale.ROOT))
                    ,SwapiPlanetList.class);

            if (planetList.getCount()==1){
                swapiPlanet = planetList.getResults().get(0);
                planet.setAppearances(swapiPlanet.getFilms().size());
                //salva o planeta com a quantidade de aparicoes em filmes
                planetRepo.save(planet);

            } else if (planetList.getCount()!=1){
                planet.setAppearances(0);
                //retornar excecao que diz que o nome deve ser unico
                //salva o planeta com as aparicoes zeradas, ja que a busca retornou mais de um planeta
                planetRepo.save(planet);

            } else throw new NullPointerException();
        }
    }

    public SwapiPlanet findOneSwapiPlanet(String id) throws NullPointerException{

        Mono<SwapiPlanet> monoSwapiPlanet = this.webClient
                .get()
                .uri("planets/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SwapiPlanet.class);
        // req async
        return monoSwapiPlanet.block();
    }

    public List<SwapiPlanet> getAllSwapiPlanets() {
        int page = 1;
        List<SwapiPlanet> swapiAllPlanets = new ArrayList<>();
        Mono<SwapiPlanetList> monoPlanet;
        //AQUI TA DANDO TUDO CERTO
        while(page<=6) {
            monoPlanet = this.webClient
                    .get()
                    .uri("planets/?page=" + page)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(SwapiPlanetList.class);
            swapiAllPlanets.addAll(monoPlanet.block().getResults());
            page++;
        }
        return swapiAllPlanets;
    }

    //Construtor
    public PlanetService(PlanetRepo planetRepo, SwapiPlanetRepo swapiPlanetRepo) {
        this.planetRepo = planetRepo;
        this.swapiPlanetRepo = swapiPlanetRepo;
    }

}
