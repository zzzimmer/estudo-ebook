package org.zzzimmer.estudoebook.domain;

import jakarta.persistence.*;

@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int quantidade;
    @Column(name = "qtd_max")
    private int quantidadeMaxima;
    @Column(name = "qtd_min")
    private int quantidadeMinima;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private ESituacao situacao = ESituacao.ATIVO;

    @OneToOne
    @MapsId // garante que o id do estoque seja igual o id do produto
    @JoinColumn(name = "produto_id")
    private Produto produto;

    //associa o produto a um determinado estoque
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

//    public Produto getProduto() {
//        return produto;
//    }

    public void repor (int quantidade) throws Exception {
        if (this.situacao != ESituacao.ATIVO){
            throw new Exception("O estoque esta INATIVO ou BLOQUEADO." +
                    " Reposição não permitida.");
        }
        if (this.quantidade + quantidade > quantidadeMaxima){
            throw new Exception("Essa operação excede o limite máximo de estoque.");
        }
        this.quantidade+=quantidade;
    }

    public void retirada(int quantidade) throws Exception{
        if (this.situacao != ESituacao.ATIVO){
            throw new Exception("O Estoque está inativo ou bloqueado." +
                    "Retirada não autorizada");
        }
        if (this.quantidade-quantidade < 0){
            throw new Exception("Quantidade de retirada não autorizada.");
        }
        this.quantidade-=quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(Integer quantidade_maxima) {
        this.quantidadeMaxima = quantidade_maxima;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidade_minima) {
        this.quantidadeMinima = quantidade_minima;
    }
}
