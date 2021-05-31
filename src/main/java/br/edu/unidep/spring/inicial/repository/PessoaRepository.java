package br.edu.unidep.spring.inicial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidep.spring.inicial.model.Categoria;
import br.edu.unidep.spring.inicial.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
