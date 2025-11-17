package org.zzzimmer.estudoebook.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/categorias/descricao/{desc}")
    public ResponseEntity<List<Categoria>> findByDescricao(@PathVariable String desc){
        List<Categoria> listDescricao = categoriaRepository.findByDescricao(desc);
        return ResponseEntity.ok(listDescricao);
    }

    @PostMapping("/categorias")
    public Categoria create(@RequestBody Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> update(@RequestBody Categoria categoria,
                                            @PathVariable Integer id){
        if (!categoriaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }else {
            categoria.setId(id);
            //setar esse id é importante porque o SDJPA verifica se existe
            //o id na base, caso sim, executa a operação de update no banco
            Categoria categoriaAtualizada = categoria;
            return ResponseEntity.ok(categoriaRepository.save(categoriaAtualizada));
        }
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        if (!categoriaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }else {
            categoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

//    De fato, para projetos pequenos não há tanto problema(acessar repository pelo controller,
//    mas o ideal
//    é separar do controller os códigos que tratam de rotinas lógicas de negócio da
//    aplicação. Em outras palavras, deve-se tirar do controller as regras de negócio,
//    fazendo dele um mediador entre os endpoints da aplicação e os serviços da API,
//    assunto esse que abordaremos no tópico “Classes de serviço de domínio”

}
