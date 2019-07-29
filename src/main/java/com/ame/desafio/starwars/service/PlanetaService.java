package com.ame.desafio.starwars.service;

import com.ame.desafio.starwars.model.entity.Planeta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanetaService {

    /**
     *  Método para salvar um planeta no banco de dados.
     * @param planeta Recebe uma instancia de planeta com os dados adicionados pelo usuário.
     * @return planeta
     */
    Planeta save(Planeta planeta);

    /**
     * Método para recuperar todos planetas salvos no banco de dados
     * @return Lista de planetas encontrados no banco de dados
     */
    List<Planeta> findAll();

    /**
     *  Método para procurar planetas no banco de dados, fazendo a query por nome.
     * @param nome Nome do planeta a ser pesquisado.
     * @return Retorna um Planeta se for encontrado na busca.
     */
    Planeta findByName(String nome);

    /**
     * Método para procurar planetas no banco de dados, fazendo a query por id.
     * @param id Id a ser pesquisado.
     * @return Retorna um Planeta se for encontrado na busca..
     */
    Planeta findById(Long id);

    /**
     * Método para deletar registro de planetas do banco de dados, baseado em eu id.
     * @param id id passado como parametro.
     */
    void delete(Long id);

}
