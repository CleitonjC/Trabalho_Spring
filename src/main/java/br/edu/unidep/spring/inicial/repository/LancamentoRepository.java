package br.edu.unidep.spring.inicial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidep.spring.inicial.model.Lancamento;


public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
