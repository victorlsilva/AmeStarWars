package com.ame.desafio.starwars.service;

import com.ame.desafio.starwars.model.entity.Planeta;
import com.ame.desafio.starwars.model.repository.PlanetaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;

	public Planeta save(Planeta planeta){
		try {
			return planetaRepository.save(planeta);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void delete(Long id) throws ObjectNotFoundException{
		planetaRepository.deleteById(id);
	}

	public Planeta findByName(String nome){
		return planetaRepository.findByNomeIgnoreCase(nome).orElseThrow(() -> new ObjectNotFoundException("Planet not found."));;
	}




}
