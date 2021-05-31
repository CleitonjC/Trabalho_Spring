package br.edu.unidep.spring.inicial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.spring.inicial.model.Categoria;
import br.edu.unidep.spring.inicial.model.Pessoa;
import br.edu.unidep.spring.inicial.repository.CategoriaRepository;
import br.edu.unidep.spring.inicial.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepositorio;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepositorio.findOne(codigo);
		
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepositorio.save(pessoaSalva);
	}

}
