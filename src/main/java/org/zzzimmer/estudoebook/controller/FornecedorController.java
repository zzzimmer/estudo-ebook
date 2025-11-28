package org.zzzimmer.estudoebook.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zzzimmer.estudoebook.domain.Fornecedor;
import org.zzzimmer.estudoebook.exceptions.NegocioEstoqueExceptions;
import org.zzzimmer.estudoebook.repository.FornecedorRepository;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public List<Fornecedor> listar (){
        return fornecedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Integer id){
        return fornecedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Fornecedor> create(@Valid @RequestBody Fornecedor fornecedor){

        boolean hasEmail = fornecedorRepository.findByEmail(fornecedor.getEmail())
                .isPresent();
        if (hasEmail){
            throw new NegocioEstoqueExceptions(
                    "Este e-mail já está cadastrado na base de dados");
        }

        return ResponseEntity.ok(fornecedorRepository.save(fornecedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable Integer id
            ,@Valid @RequestBody Fornecedor fornecedorPayload){

        boolean Email = fornecedorRepository.findByEmail(fornecedorPayload.getEmail())
                .filter(fornecedorRepositorio -> !fornecedorRepositorio.equals(fornecedorPayload))
                .isPresent();

        if (Email){
            throw new NegocioEstoqueExceptions(
                    "Este e-mail já está cadastrado na base de dados");
        }

        if (!fornecedorRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        fornecedorPayload.setId(id);//necessário setar nessa instância porque ela contem os dados
        //atualizados. A partir disso, o méthodo pode funcionar normalmente
        return ResponseEntity.ok(fornecedorRepository.save(fornecedorPayload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        if (!fornecedorRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        fornecedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
