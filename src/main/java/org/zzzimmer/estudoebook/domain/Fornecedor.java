package org.zzzimmer.estudoebook.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fornecedor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String name;
    private String email;
    private String fone;

    @OneToMany(mappedBy = "fornecedor")
    @JsonIgnoreProperties("fornecedor")
    private List<Produto> produtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void add (Produto produto){
        this.produtos.add(produto);
        produto.setFornecedor(this);
    }

    public void remove(Produto produto){
        this.produtos.remove(produto);
        produto.setFornecedor(null);
    }
}
