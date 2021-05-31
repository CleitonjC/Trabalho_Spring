package br.edu.unidep.spring.inicial.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unidep.spring.inicial.event.RecursoCriadoEvent;
import br.edu.unidep.spring.inicial.model.Usuario;
import br.edu.unidep.spring.inicial.service.UsuarioService;
import br.edu.unidep.spring.inicial.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	

	@Autowired
	UsuarioRepository usuarioRepositorio;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/ok")
	public String ok() {
		return "ok";
	}
	

	@GetMapping
	public List<Usuario> listar() {
		List<Usuario> usuario = usuarioRepositorio.findAll();
		
		if (usuario.size() == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return usuario;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		
		Usuario usuarioSalva = usuarioRepositorio.save(usuario);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalva);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @RequestBody Usuario usuario) {
		
		Usuario usuarioSalva = usuarioService.atualizar(codigo, usuario);
		return ResponseEntity.ok(usuarioSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		usuarioRepositorio.delete(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario usuario = usuarioRepositorio.findOne(codigo);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
}
