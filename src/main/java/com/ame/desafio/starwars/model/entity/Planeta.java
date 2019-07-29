package com.ame.desafio.starwars.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Planeta")
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(name = "Nome", nullable = false, unique = true)
    private String nome;
    @Column(name = "Clima", nullable = false)
    private String clima;
    @Column(name = "Terreno", nullable = false)
    private String terreno;
    @Column(name = "Aparicoes_Filme")
    private int aparicoesFilmes;

    public Planeta() {}

    public Planeta(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planeta planeta = (Planeta) o;
        return id == planeta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public int getAparicoesFilmes() {
        return aparicoesFilmes;
    }

    public void setAparicoesFilmes(int aparicoesFilmes) {
        this.aparicoesFilmes = aparicoesFilmes;
    }
}
