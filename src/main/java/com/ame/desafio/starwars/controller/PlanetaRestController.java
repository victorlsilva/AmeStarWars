package com.ame.desafio.starwars.controller;

import com.ame.desafio.starwars.model.entity.Planeta;
import com.ame.desafio.starwars.service.PlanetaService;
import com.ame.desafio.starwars.service.PlanetaServiceIntegrationAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/planetas")
public class PlanetaRestController {

	@Autowired
	private PlanetaService planetaService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> insert(@RequestBody Planeta planet){
		Planeta planeta = planetaService.save(planet);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				          .buildAndExpand(planeta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Planeta searchById(@PathVariable Long id){
		return planetaService.findById(id);
	}

	@GetMapping(value = "/search",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Planeta searchByName(@RequestParam(name = "name", required = true)String name){
		return planetaService.findByName(name);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Planeta> findAll(){
		return planetaService.findAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id){
		planetaService.delete(id);
	}
}
