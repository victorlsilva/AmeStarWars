package com.ame.desafio.starwars;

import com.ame.desafio.starwars.exceptions.ObjectNotFoundException;
import com.ame.desafio.starwars.model.entity.Planeta;
import com.ame.desafio.starwars.model.repository.PlanetaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = StarwarsApplication.class)
public class RestControllerIntegrationTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    private PlanetaRepository planetaRepository;

    private List<Planeta> planetas;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        planetas = createList();
    }

    @Test
    public void whenGetAllPlanets_thenReturnJsonArray() throws Exception {
        given(planetaRepository.findAll()).willReturn(planetas);
        mockMvc.perform(get("/api/planetas/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value(planetas.get(0).getNome()));
    }

    @Test
    public void whenGetById_thenReturnJsonObject() throws Exception {
        Planeta planeta = planetas.get(0);
        given(planetaRepository.findById(1L)).willReturn(Optional.of(planeta));
        mockMvc.perform(get("/api/planetas/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(planeta.getNome()));
    }

    @Test
    public void whenGetById_thenReturn404() throws Exception {
        given(planetaRepository.findById(anyLong())).willThrow(ObjectNotFoundException.class);
        mockMvc.perform(get("/api/planetas/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenSearchByName_thenReturnJsonObject() throws Exception {
        Planeta planeta = planetas.get(0);
        given(planetaRepository.findByNomeIgnoreCase(planeta.getNome())).willReturn(Optional.of(planeta));
        mockMvc.perform(get("/api/planetas/search?name=" + planeta.getNome())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(planeta.getNome()));
    }

    @Test
    public void whenSearchByName_thenReturn404() throws Exception {
        given(planetaRepository.findByNomeIgnoreCase(anyString())).willThrow(ObjectNotFoundException.class);
        mockMvc.perform(get("/api/planetas/search?name=testName")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void whenDeleteById_thenReturn201() throws Exception {
        doNothing().when(planetaRepository).deleteById(anyLong());
        mockMvc.perform(delete("/api/planetas/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeleteById_thenReturn404() throws Exception {
        doThrow(ObjectNotFoundException.class).when(planetaRepository).deleteById(anyLong());
        mockMvc.perform(delete("/api/planetas/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenInsert_thenReturnHeaderLocation() throws Exception {
        Planeta planet = new Planeta("Tatooine", "arid", "desert");
        planet.setId(1L);
        given(planetaRepository.save(ArgumentMatchers.any(Planeta.class)))
                .willReturn(planet);
        mockMvc.perform(post("/api/planetas")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(RestControllerIntegrationTest.mapToJson(planet)))
                .andExpect(status().isCreated());
//                .andExpect(header().exists("location"));
    }

    @Test
    public void whenInsert_thenReturn400() throws Exception {
        Planeta planet = new Planeta();
        mockMvc.perform(post("/api/planetas")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest());
    }

    private static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public static List<Planeta> createList() {
        return Lists.newArrayList(
                new Planeta("Tatooine", "Arid", "Desert"),
                new Planeta("Alderaan", "temperate", "jungle")
        );
    }
}