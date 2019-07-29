package com.ame.desafio.starwars;

import com.ame.desafio.starwars.model.entity.Planeta;
import com.ame.desafio.starwars.model.repository.PlanetaRepository;
import com.ame.desafio.starwars.exceptions.ObjectNotFoundException;
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
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = StarwarsApplication.class)
public class StarwarsApplicationTests {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    private PlanetaRepository planetaRepository;

    public List<Planeta> planetas;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        planetas = createList();
    }

    @Test
    public void whenGetAllPlanets_thenReturnJsonArray() throws Exception{
        given(planetaRepository.findAll()).willReturn(planetas);
        mockMvc.perform(get("/api/planetas/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(planetas.get(0).getNome()));
    }

    @Test
    public void whenGetById_thenReturnJsonObject() throws Exception{
        Planeta planeta = planetas.get(0);
        given(planetaRepository.findById(1L)).willReturn(Optional.of(planeta));
        mockMvc.perform(get("/api/planetas/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(planeta.getNome()));
    }

    @Test
    public void whenGetById_thenReturn404() throws Exception{
        given(planetaRepository.findById(anyLong())).willThrow(ObjectNotFoundException.class);
        mockMvc.perform(get("/api/planetas/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenSearchByName_thenReturnJsonObject() throws Exception{
        Planeta planeta = planetas.get(0);
        given(planetaRepository.findByNomeIgnoreCase(planeta.getNome())).willReturn(Optional.of(planeta));
        mockMvc.perform(get("/api/planetas/search?name="+planeta.getNome())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(planeta.getNome()));
    }

    @Test
    public void whenSearchByName_thenReturn404() throws Exception{
        given(planetaRepository.findByNomeIgnoreCase(anyString())).willThrow(ObjectNotFoundException.class);
        mockMvc.perform(get("/api/planetas/search?name=testName")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());

    }

    @Test
    public void whenDeleteById_thenReturn201()throws Exception{
        doNothing().when(planetaRepository).deleteById(anyLong());
        mockMvc.perform(delete("/api/planetas/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeleteById_thenReturn404()throws Exception{
        doThrow(ObjectNotFoundException.class).when(planetaRepository).deleteById(anyLong());
        mockMvc.perform(delete("/api/planetas/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenInsert_thenReturnHeaderLocation()throws Exception{
        Planeta planet = new Planeta("Tattoine", "arid", "desert");
        given(planetaRepository.save(ArgumentMatchers.any(Planeta.class)))
                .willReturn(planet);
        mockMvc.perform(post("/api/planetas")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(StarwarsApplicationTests.mapToJson(planet)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }

    @Test
    public void whenInsert_thenReturnErrorList()throws Exception{
        Planeta planet = new Planeta();
        mockMvc.perform(post("/api/planetas")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(StarwarsApplicationTests.mapToJson(planet)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", notNullValue()));
    }

    private static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public static List<Planeta> createList(){
        return Lists.newArrayList(
                new Planeta("Tattooine","Arid","Desert"),
                new Planeta("Alderaan", "temperate", "jungle")
        );
    }
}
