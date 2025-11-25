package org.zzzimmer.estudoebook.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Fornecedor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "nome")
    private String name;
    private String email;
    private String fone;

    @OneToMany(mappedBy = "fornecedor")
    @JsonIgnoreProperties("fornecedor")
    private List<Produto> produtos;

    public void add (Produto produto){
        this.produtos.add(produto);
        produto.setFornecedor(this);
    }

    public void remove(Produto produto){
        this.produtos.remove(produto);
        produto.setFornecedor(null);
    }
}
