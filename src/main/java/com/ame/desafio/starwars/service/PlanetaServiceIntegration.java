package com.ame.desafio.starwars.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

public class PlanetaServiceIntegration {

    RestTemplate restTemplate = new RestTemplate();


    private void constructUri(String name) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("swapi.co").path("/api/planets").query(name).build();

    }

    private HttpHeaders createHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return headers;
    }

}
