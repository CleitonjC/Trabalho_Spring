package br.edu.unidep.spring.inicial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidep.spring.inicial.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}