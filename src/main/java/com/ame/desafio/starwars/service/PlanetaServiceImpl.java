package com.ame.desafio.starwars.service;

import com.ame.desafio.starwars.model.entity.Planeta;
import com.ame.desafio.starwars.model.entity.PlanetaDTO;
import com.ame.desafio.starwars.model.repository.PlanetaRepository;
import com.google.common.collect.Lists;
import com.ame.desafio.starwars.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ame.desafio.starwars.exceptions.ObjectNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;

	@Autowired
	private PlanetaServiceIntegrationAccess planetaServiceIntegrationAccess;

	public Planeta save(Planeta planeta){
		PlanetaDTO planetaHelper = null;
		try {
			if (planeta != null)
				planetaHelper = planetaServiceIntegrationAccess.searchByName(planeta.getNome());

			if (planetaHelper != null || planetaHelper.getQuantity_movies() != 0) {
					planeta.setAparicoesFilmes(planetaHelper.getQuantity_movies());
					return planetaRepository.save(planeta);
			}else{
				throw new DatabaseException("Erro ao salvar dados.");
			}
		}catch (Exception e){
			throw new DatabaseException("Erro ao salvar dados.", e);
		}
	}

	public List<Planeta> findAll() {
		List<Planeta> planetas = new ArrayList<Planeta>();
		try {
			planetas = Lists.newArrayList(planetaRepository.findAll());
			return planetas;
		}catch (Exception e){
				e.printStackTrace();
		}

		return planetas;

	}

	public Planeta findByName(String nome) {
		return planetaRepository.findByNomeIgnoreCase(nome).orElseThrow(() -> new ObjectNotFoundException(" O Planeta não foi encontrado."));
	}

	public Planeta findById(Long id) {
		Optional<Planeta> planeta = planetaRepository.findById(id);
		return planeta.orElseThrow(() -> new ObjectNotFoundException(" O Planeta não foi encontrado."));
	}

	public void delete(Long id) {
		try {
			planetaRepository.deleteById(id);
		}catch (Exception e){
			throw new ObjectNotFoundException("O Planeta não foi encontrado.");
		}
	}

}
