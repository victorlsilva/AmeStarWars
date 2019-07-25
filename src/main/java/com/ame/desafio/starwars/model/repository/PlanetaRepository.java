package com.ame.desafio.starwars.model.repository;

import com.ame.desafio.starwars.model.entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {

        @Transactional
        Optional<Planeta> findByNomeIgnoreCase(String nome);

}
