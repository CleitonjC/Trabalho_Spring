package br.edu.unidep.spring.inicial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.spring.inicial.model.Categoria;
import br.edu.unidep.spring.inicial.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepositorio;

	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = categoriaRepositorio.findOne(codigo);
		
		
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		return categoriaRepositorio.save(categoriaSalva);
	}

}
