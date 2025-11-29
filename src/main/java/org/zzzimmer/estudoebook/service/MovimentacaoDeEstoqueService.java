package org.zzzimmer.estudoebook.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzzimmer.estudoebook.domain.Produto;
import org.zzzimmer.estudoebook.repository.ProdutoRepository;

@Service
@AllArgsConstructor
public class MovimentacaoDeEstoqueService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscarProdutoPorId(int id) throws Exception {
        return produtoRepository.findById(id).orElseThrow(
                () -> new Exception("Produto não consta no estoque")
        );
    }

    public Produto repor(int id, int quantidade) throws Exception {

        Produto produto = buscarProdutoPorId(id);
        produto.getEstoque().repor(quantidade);
        produtoRepository.save(produto);

        return produto;
    }

    public Produto retirar(int id, int quantidade) throws Exception {
        Produto produto = buscarProdutoPorId(id);
        produto.getEstoque().retirar(quantidade);
        return produtoRepository.save(produto);
    }
}
