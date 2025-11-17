package org.zzzimmer.estudoebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zzzimmer.estudoebook.domain.Produto;
import org.zzzimmer.estudoebook.repository.ProdutoRepository;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }
}
