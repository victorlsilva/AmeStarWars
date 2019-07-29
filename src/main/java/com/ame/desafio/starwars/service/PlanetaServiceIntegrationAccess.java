package com.ame.desafio.starwars.service;

import com.ame.desafio.starwars.model.entity.PlanetaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanetaServiceIntegrationAccess {

    /**
     * Recupera os planetas achados na api StarWars.
     * @return Lista com todos os planetas encontrados.
     */
    List<PlanetaDTO> searchAll();

    /**
     * Procura os planetas na api Star Wars por nome.
     * @param name
     * @return Planeta encontrado na busca a api.
     */
    PlanetaDTO searchByName(String name);

}
