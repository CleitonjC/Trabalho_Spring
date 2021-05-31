package br.edu.unidep.spring.inicial.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class AppUserDetailsService implements UserDetailsService {
	@Autowired	
	private UsuarioRepository usuarioRepositorio;
	
	@Override
	   public UserDetails loadUserByUsername(String email) {
        return usuarioRepositorio.findByEmail(email);
    }
}