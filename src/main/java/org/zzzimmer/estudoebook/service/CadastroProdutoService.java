package org.zzzimmer.estudoebook.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zzzimmer.estudoebook.domain.Categoria;
import org.zzzimmer.estudoebook.domain.Fornecedor;
import org.zzzimmer.estudoebook.domain.Produto;
import org.zzzimmer.estudoebook.repository.CategoriaRepository;
import org.zzzimmer.estudoebook.repository.FornecedorRepository;
import org.zzzimmer.estudoebook.repository.ProdutoRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Transactional
    public Produto salvar(Produto payload){
        Produto produto = (produtoRepository.save(payload));

        Optional<Categoria> categoria = categoriaRepository.findById(
                payload.getCategoria().getId());
        produto.setCategoria(categoria.get());

        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(
                payload.getFornecedor().getId());
        produto.setFornecedor(fornecedor.get());

        return produto;
    }

    @Transactional
    public void excluir(Integer id){
        produtoRepository.deleteById(id);
    }

//    public Optional<Produto> findById(Integer id) throws Exception {
//        return produtoRepository.findById(id).orElseThrow(
//                () -> new Exception("Produto não encontrado no banco de dados")
//        )
//    }
}
