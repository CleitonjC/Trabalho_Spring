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
import br.edu.unidep.spring.inicial.model.Categoria;
import br.edu.unidep.spring.inicial.model.Lancamento;
import br.edu.unidep.spring.inicial.repository.LancamentoRepository;
import br.edu.unidep.spring.inicial.service.LancamentoService;



@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	

	@Autowired
	LancamentoRepository lancamentoRepositorio;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/ok")
	public String ok() {
		return "ok";
	}
	

	@GetMapping
	public List<Lancamento> listar() {
		List<Lancamento> lancamento = lancamentoRepositorio.findAll();
		
		if (lancamento.size() == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return lancamento;
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		
		Lancamento lancamentoSalva = lancamentoRepositorio.save(lancamento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long codigo, @RequestBody Lancamento lancamento) {
		
		Lancamento lancamentoSalva = lancamentoService.atualizar(codigo, lancamento);
		return ResponseEntity.ok(lancamentoSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		lancamentoRepositorio.delete(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
		Lancamento lancamento = lancamentoRepositorio.findOne(codigo);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}

}
