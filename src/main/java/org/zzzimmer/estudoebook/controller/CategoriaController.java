package org.zzzimmer.estudoebook.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zzzimmer.estudoebook.domain.Categoria;
import org.zzzimmer.estudoebook.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public List<Categoria> listar(){
        //metodo provido pela SDJPA
        return categoriaRepository.findAll();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()){
            return ResponseEntity.ok(categoria.get());
        }
        return ResponseEntity.notFound().build();
    }
}
