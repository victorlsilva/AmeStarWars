package com.ame.desafio.starwars.repository;

import com.ame.desafio.starwars.model.Planeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PlanetaRepository {

    @Repository
    public interface PlanetRepository extends CrudRepository<Planeta, Long> {
        @Transactional
        Optional<Planeta> findByNameIgnoreCase(String name);
    }

}
