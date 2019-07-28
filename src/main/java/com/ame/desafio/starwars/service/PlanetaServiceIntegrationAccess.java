package com.ame.desafio.starwars.service;

import com.ame.desafio.starwars.model.entity.PlanetaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanetaServiceIntegrationAccess {

    List<PlanetaDTO> searchAll();

    PlanetaDTO searchByName(String name);

}
