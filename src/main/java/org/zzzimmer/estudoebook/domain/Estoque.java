package org.zzzimmer.estudoebook.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @Getter // implementar somente getter para preservar a integridade
    private int quantidade;
    @Column(name = "qtd_max")
    private int quantidadeMaxima;
    @Column(name = "qtd_min")
    private int quantidadeMinima;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private ESituacao situacao = ESituacao.ATIVO;

    @OneToOne
    @MapsId // garante que o id do estoque seja igual o id do produto. Strong Ownership
    @JoinColumn(name = "produto_id")
    @JsonIgnoreProperties("estoque")
    private Produto produto;

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

    public void retirar(int quantidade) throws Exception{
        if (this.situacao != ESituacao.ATIVO){
            throw new Exception("O Estoque está inativo ou bloqueado." +
                    "Retirada não autorizada");
        }
        if (this.quantidade-quantidade < 0){
            throw new Exception("Quantidade de retirada não autorizada.");
        }
        this.quantidade-=quantidade;
    }
}
