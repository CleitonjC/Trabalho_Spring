package br.edu.unidep.spring.inicial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.spring.inicial.model.Lancamento;
import br.edu.unidep.spring.inicial.repository.LancamentoRepository;


@Service
public class LancamentoService {
	
	@Autowired
	LancamentoRepository lancamentoRepositorio;

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalva = lancamentoRepositorio.findOne(codigo);
		
		
		BeanUtils.copyProperties(lancamento, lancamentoSalva, "codigo");
		return lancamentoRepositorio.save(lancamentoSalva);
	}

}
