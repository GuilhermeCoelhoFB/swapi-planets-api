package com.monnos.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanetRepo extends MongoRepository <Planet, String>{

    Optional<Planet> findPlanetByName(String name);

    Optional<Planet> findById(String id);

}
