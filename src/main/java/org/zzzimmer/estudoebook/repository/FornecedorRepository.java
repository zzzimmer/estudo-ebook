package org.zzzimmer.estudoebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zzzimmer.estudoebook.domain.Fornecedor;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    Optional<Fornecedor> findByEmail(String email);

}
