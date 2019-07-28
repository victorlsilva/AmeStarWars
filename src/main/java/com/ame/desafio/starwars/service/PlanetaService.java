package com.ame.desafio.starwars.service;

import com.ame.desafio.starwars.model.entity.Planeta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanetaService {

    Planeta save(Planeta planeta);

    List<Planeta> findAll();

    Planeta findByName(String nome);

    Planeta findById(Long id);

    void delete(Long id);

}
