package com.monnos.repository;

import com.monnos.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepo extends MongoRepository <Planet, String>{

    Optional<Planet> findPlanetByName(String name);
}
