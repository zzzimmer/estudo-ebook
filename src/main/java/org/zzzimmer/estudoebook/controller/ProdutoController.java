package org.zzzimmer.estudoebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zzzimmer.estudoebook.domain.Categoria;
import org.zzzimmer.estudoebook.domain.Produto;
import org.zzzimmer.estudoebook.repository.CategoriaRepository;
import org.zzzimmer.estudoebook.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/produtos")
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> GetById(@PathVariable Integer id){
        return produtoRepository.findById(id).map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/produtos")
    public ResponseEntity<Produto> create(@RequestBody Produto payload){
        Produto produto = (produtoRepository.save(payload));

        Optional<Categoria> categoria = categoriaRepository.findById(
                payload.getCategoria().getId());
        produto.setCategoria(categoria.get());
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Integer id, @RequestBody Produto produto){

        if (!produtoRepository.existsById(id)){
            ResponseEntity.notFound().build();
        }
        produto.setId(id);
        Produto entidadeAtualizada = produtoRepository.save(produto);
        return ResponseEntity.ok(entidadeAtualizada);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){

        if (!produtoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
