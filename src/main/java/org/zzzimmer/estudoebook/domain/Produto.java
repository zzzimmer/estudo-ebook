package org.zzzimmer.estudoebook.domain;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Produto precisa de um nome")
    @Size(max = 50, message = "Este campo aceita até 200 caracteres")
    private String nome;
    @NotBlank(message = "Produto precisa de uma descrição")
    @Size(max = 200)
    private String descricao;
    @NotNull(message = "O produto precisa de um preço")
    private BigDecimal preco;

    @NotNull(message = "Indique o fornecedor do produto")
    @ManyToOne
    @JsonIgnoreProperties("produtos") //ao "entrar" no objeto, ignora determinada propriedade
    private Fornecedor fornecedor;

    @NotNull(message = "Indique a categoria do produto")
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
