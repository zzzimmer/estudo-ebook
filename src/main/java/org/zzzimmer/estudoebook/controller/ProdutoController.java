package org.zzzimmer.estudoebook.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zzzimmer.estudoebook.domain.Categoria;
import org.zzzimmer.estudoebook.domain.Fornecedor;
import org.zzzimmer.estudoebook.domain.Produto;
import org.zzzimmer.estudoebook.repository.CategoriaRepository;
import org.zzzimmer.estudoebook.repository.FornecedorRepository;
import org.zzzimmer.estudoebook.repository.ProdutoRepository;
import org.zzzimmer.estudoebook.service.CadastroProdutoService;
import org.zzzimmer.estudoebook.service.MovimentacaoDeEstoqueService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private MovimentacaoDeEstoqueService movimentacaoDeEstoqueService;

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> GetById(@PathVariable Integer id) {
        return produtoRepository.findById(id).map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//retorna codigo 201
    public ResponseEntity<Produto> create(@Valid @RequestBody Produto payload) {
        Produto produto = cadastroProdutoService.salvar(payload);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Integer id,
                                             @Valid @RequestBody Produto produto) {
//        if (!cadastroProdutoService.findById(id).isPresent())
            produto.setId(id);
            Produto entidadeAtualizada = cadastroProdutoService.salvar(produto);
            return ResponseEntity.ok(entidadeAtualizada);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cadastroProdutoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/repor")
    public ResponseEntity<Produto> reporEstoque(@PathVariable int id,
                                                @RequestParam int quantidade) throws Exception {
            Produto produto = movimentacaoDeEstoqueService.repor(id, quantidade);
            return ResponseEntity.ok(produto);

    }

    @PutMapping("/{id}/retirar")
    public ResponseEntity<Produto> retirarEstoque(@PathVariable int id,
                                                  @RequestParam int quantidade) throws Exception {
        Produto produto = movimentacaoDeEstoqueService.retirar(id, quantidade);
        return ResponseEntity.ok(produto);
    }


}
