package org.zzzimmer.estudoebook.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zzzimmer.estudoebook.domain.Categoria;
import org.zzzimmer.estudoebook.repository.CategoriaRepository;

import java.util.List;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/categorias")
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }
}
