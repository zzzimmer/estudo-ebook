package org.zzzimmer.estudoebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zzzimmer.estudoebook.domain.Fornecedor;
import org.zzzimmer.estudoebook.repository.FornecedorRepository;

import java.util.List;

@RestController
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping("/fornecedores")
    public List<Fornecedor> listar (){
        return fornecedorRepository.findAll();
    }

    @GetMapping("/fornecedores/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Integer id){
        return fornecedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/fornecedores")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Fornecedor> create(@RequestBody Fornecedor fornecedor){
        return ResponseEntity.ok(fornecedorRepository.save(fornecedor));
    }

    @PutMapping("/fornecedores/{id}")
    public ResponseEntity<Fornecedor> update(
            @PathVariable Integer id
            ,@RequestBody Fornecedor fornecedor){
        if (!fornecedorRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        fornecedor.setId(id);//necessário setar nessa instância porque ela contem os dados
        //atualizados. A partir disso, o méthodo pode funcionar normalmente
        return ResponseEntity.ok(fornecedorRepository.save(fornecedor));
    }

    @DeleteMapping("/fornecedores/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        if (!fornecedorRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        fornecedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
