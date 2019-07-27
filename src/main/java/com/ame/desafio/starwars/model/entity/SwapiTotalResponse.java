package com.ame.desafio.starwars.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SwapiTotalResponse {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Long count;
    @JsonIgnore
    private String next;
    @JsonIgnore
    private String previous;

    private List<PlanetaDTO> results;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PlanetaDTO> getResults() {
        return results;
    }

    public void setResults(List<PlanetaDTO> results) {
        this.results = results;
    }
}
