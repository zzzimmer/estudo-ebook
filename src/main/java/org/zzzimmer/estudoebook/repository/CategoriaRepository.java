package org.zzzimmer.estudoebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zzzimmer.estudoebook.domain.Categoria;

import java.util.List;

public interface CategoriaRepository extends JpaRepository <Categoria, Integer> {

    List<Categoria> findByDescricao(String descricao);
}
