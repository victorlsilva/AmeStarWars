package com.ame.desafio.starwars.service.impl;

import com.ame.desafio.starwars.model.entity.Planeta;
import com.ame.desafio.starwars.model.repository.PlanetaRepository;
import com.ame.desafio.starwars.service.PlanetasService;
import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PlanetaServiceImpl implements PlanetasService {

	@Autowired
	private PlanetaRepository planetaRepository;

	public Planeta save(Planeta planeta){
			return planetaRepository.save(planeta);
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
