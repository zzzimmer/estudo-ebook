package org.zzzimmer.estudoebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zzzimmer.estudoebook.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
