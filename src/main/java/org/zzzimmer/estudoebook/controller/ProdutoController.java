package org.zzzimmer.estudoebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zzzimmer.estudoebook.domain.Categoria;
import org.zzzimmer.estudoebook.domain.Produto;
import org.zzzimmer.estudoebook.repository.CategoriaRepository;
import org.zzzimmer.estudoebook.repository.ProdutoRepository;

import java.util.List;

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

        Categoria categoria = categoriaRepository.getReferenceById(
                payload.getCategoria().getId());
        produto.setCategoria(categoria);
        return ResponseEntity.ok(produto);
    }
}
