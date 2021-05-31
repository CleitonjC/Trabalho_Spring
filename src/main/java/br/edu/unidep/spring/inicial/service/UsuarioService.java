package br.edu.unidep.spring.inicial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.spring.inicial.model.Usuario;
import br.edu.unidep.spring.inicial.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepositorio;

	public Usuario atualizar(Long codigo, Usuario usuario) {
		Usuario usuarioSalva = usuarioRepositorio.findOne(codigo);
		
		
		BeanUtils.copyProperties(usuario, usuarioSalva, "codigo");
		return usuarioRepositorio.save(usuarioSalva);
	}

}
