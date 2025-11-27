package org.zzzimmer.estudoebook.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Insira o nome")
    @Size(max = 50, message = "Insira até 50 caracteres")
    @Column(name = "nome")
    private String name;
    @NotBlank(message = "Insira e-mail")
    @Size(max = 100, message = "E-mail obrigatório")
    private String email;
    @NotBlank(message = "Telefone obrigatório")
    @Size(max = 25, message = "Até 25 números no telefone.")
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
