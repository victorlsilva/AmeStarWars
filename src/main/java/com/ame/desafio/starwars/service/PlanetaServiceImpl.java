package com.ame.desafio.starwars.service;

import com.ame.desafio.starwars.model.entity.Planeta;
import com.ame.desafio.starwars.model.entity.PlanetaDTO;
import com.ame.desafio.starwars.model.repository.PlanetaRepository;
import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		PlanetaDTO planetaHelper;
		planetaHelper = planetaServiceIntegrationAccess.searchByName(planeta.getNome());

		if (planeta.getNome().equals(planetaHelper.getName())){
			planeta.setAparicoesFilmes(planetaHelper.getQuantity_movies());
			return planetaRepository.save(planeta);
		} else{
			return planetaRepository.save(planeta);
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
		return planetaRepository.findByNomeIgnoreCase(nome).orElseThrow(() -> new ObjectNotFoundException(nome," O Planeta não foi encontrado."));
	}

	public Planeta findById(Long id) {
		Optional<Planeta> planeta = planetaRepository.findById(id);
		return planeta.orElseThrow(() -> new ObjectNotFoundException(id ," O Planeta não foi encontrado."));
	}

	public void delete(Long id) {
		try {
			planetaRepository.deleteById(id);
		}catch (Exception e){
			throw new ObjectNotFoundException(id ,"O Planeta não foi encontrado.");
		}
	}

}
