package com.adalbertosolf.api.controller;

//import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.BeanUtils;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adalbertosolf.domain.exception.EntidadeEmUsoException;
import com.adalbertosolf.domain.exception.EntidadeNaoEncontradaException;
import com.adalbertosolf.domain.model.Cliente;
import com.adalbertosolf.domain.repository.ClienteRepository;
import com.adalbertosolf.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
//@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CadastroClienteService cadastroCliente;
	/*
	@PersistenceContext
	private EntityManager manager;
	*/
	@GetMapping
	public List<Cliente> listar() {
		/*
		Cliente cliente = new Cliente(); 
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente);
		return clientes;
		*/
		return clienteRepository.listar();
		//return clienteRepository.findAll();
		
		//return manager.createQuery("from Cliente",Cliente.class).getResultList();
	}
	
	@GetMapping("/{clienteId}")
	//public Optional<Cliente> buscar(@PathVariable("clienteId") Long clienteId) {
	//public Cliente buscar(@PathVariable("clienteId") Long clienteId) {
	public ResponseEntity<Cliente> buscar(@PathVariable("clienteId") Long clienteId) {
	
	    //return clienteRepository.buscar(clienteId);
		//return clienteRepository.findById(clienteId);
		//return manager.createQuery("from Cliente",Cliente.class).getResultList();
		
        Cliente cliente = clienteRepository.buscar(clienteId);
		
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.salvar(cliente);
		//return clienteRepository.save(cliente);
		//return manager.merge(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable("clienteId") Long clienteId,
			@RequestBody Cliente cliente) {
		Cliente clienteAtual = clienteRepository.buscar(clienteId);
		
		if (clienteAtual != null) {
			BeanUtils.copyProperties(cliente, clienteAtual, "id");
			
			clienteAtual = cadastroCliente.salvar(clienteAtual);
			return ResponseEntity.ok(clienteAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Cliente> remover(@PathVariable("clienteId") Long clienteId) {
		try {
			cadastroCliente.excluir(clienteId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
