package org.zzzimmer.estudoebook.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zzzimmer.estudoebook.domain.Categoria;

import java.util.List;

@RestController
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/categorais")
    public List<Categoria> listar(){

        TypedQuery<Categoria> pesquisa =

                entityManager.createQuery(
                        "from Categoria", Categoria.class
                );
        return pesquisa.getResultList();
    }
}
