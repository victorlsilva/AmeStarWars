package com.ame.desafio.starwars.service;

import com.ame.desafio.starwars.model.entity.PlanetaDTO;
import com.ame.desafio.starwars.model.entity.SwapiTotalResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.ame.desafio.starwars.exceptions.ObjectNotFoundException;

import java.util.Arrays;
import java.util.List;
@Service
public class PlanetaServiceIntegrationAccessImpl implements PlanetaServiceIntegrationAccess {

    private RestTemplate restTemplate = new RestTemplate();

    public List<PlanetaDTO> searchAll() {
        ResponseEntity<SwapiTotalResponse> response = restTemplate.exchange(constructUri(), HttpMethod.GET, buildEntity(), SwapiTotalResponse.class);
        if (response.getBody().getResults().isEmpty()) {
            throw new ObjectNotFoundException("No Planet was found.");
        }

        List<PlanetaDTO> planetas = response.getBody().getResults();

        planetas.forEach(planetaDTO -> {
            planetaDTO.setQuantity_movies(planetaDTO.getFilms().size());
        });
        return planetas;
    }

    public PlanetaDTO searchByName(String name) {
        ResponseEntity<SwapiTotalResponse> response = restTemplate.exchange(constructUriSearch("planets/?search=" + name), HttpMethod.GET, buildEntity(), SwapiTotalResponse.class);
        if (response.getBody().getResults().isEmpty()) {
            throw new ObjectNotFoundException("No Planet was found.");
        }
        SwapiTotalResponse responseDTO = response.getBody();
		return responseDTO.getResults().get(0);
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

    private HttpEntity buildEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity(headers);
    }

}
