package org.zzzimmer.estudoebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zzzimmer.estudoebook.domain.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Integer> {
}
