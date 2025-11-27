package org.zzzimmer.estudoebook.domain;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    @ManyToOne
    @JsonIgnoreProperties("produtos") //ao "entrar" no objeto, ignora determinada propriedade
    private Fornecedor fornecedor;

    @ManyToOne
    private Categoria categoria;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
//    @Getter// mantem somente a possibildiade de GET estoque. Visto a composição
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties("produto")
    private Estoque estoque;

    public Produto(){
        this.estoque = new Estoque();
        this.estoque.setProduto(this);
    }
}
