package com.monnos.repository;

import com.monnos.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwapiPlanetRepo extends MongoRepository<Planet, String> {

}
