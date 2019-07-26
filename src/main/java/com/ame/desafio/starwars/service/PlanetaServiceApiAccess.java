package com.ame.desafio.starwars.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
public class PlanetaServiceApiAccess {

    RestTemplate restTemplate = new RestTemplate();

	public List<PlanetDTO> searchAll(){
		ResponseEntity<ServiceResponseDTO> response = restTemplate.exchange(constructUri(), HttpMethod.GET,buildEntity(), ServiceResponseDTO.class);
		if(response.getBody().getResults().isEmpty()){
			throw new ObjectNotFoundException("findAll","No Planet was found.");
		}
		List<PlanetDTO> planets = response.getBody().getResults();
		planets.forEach(planetDTO -> {
			planetDTO.setQuantity_movies(planetDTO.getFilms().size());});
		return planets;
	}
	public PlanetDTO searchByName(String name){
		ResponseEntity<ServiceResponseDTO> response = restTemplate.exchange(constructUriSearch("planets/?search="+name), HttpMethod.GET,buildEntity(), ServiceResponseDTO.class);
		if(response.getBody().getResults().isEmpty()){
			throw new ObjectNotFoundException("findByName","No Planet was found.");
		}
		ServiceResponseDTO serviceResponseDTO = response.getBody();
		return serviceResponseDTO.getResults().get(0);
	}

    private String constructUriSearch(String name) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                                    .scheme("https")
                                    .host("swapi.co")
                                    .path("/api/planets")
                                    .queryParam("search", name)
                                    .build();

        return uri.toString();
    }

	private String constructUri() {
		UriComponents uri = UriComponentsBuilder.newInstance()
				                    .scheme("https")
				                    .host("swapi.co")
				                    .path("/api/planets")
				                    .build();

		return uri.toString();
	}




    public HttpEntity buildEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity(headers);
    }

}
