package com.adalbertosolf.api.controller;

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
import com.adalbertosolf.domain.model.Pedido;
import com.adalbertosolf.domain.repository.PedidoRepository;
import com.adalbertosolf.domain.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private CadastroPedidoService cadastroPedido;
	
	@GetMapping
	public List<Pedido> listar() {
		return pedidoRepository.listar();
	}
	
	@GetMapping("/{pedidoId}")
	public ResponseEntity<Pedido> buscar(@PathVariable("pedidoId") Long pedidoId) {
        Pedido pedido = pedidoRepository.buscar(pedidoId);
		
		if (pedido != null) {
			return ResponseEntity.ok(pedido);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Pedido adicionar(@RequestBody Pedido pedido) {
		return pedidoRepository.salvar(pedido);
	}
	
	@PutMapping("/{pedidoId}")
	public ResponseEntity<Pedido> atualizar(@PathVariable("pedidoId") Long pedidoId,
			@RequestBody Pedido pedido) {
		Pedido pedidoAtual =pedidoRepository.buscar(pedidoId);
		
		if (pedidoAtual != null) {
			BeanUtils.copyProperties(pedido, pedidoAtual, "id");
			
			pedidoAtual = cadastroPedido.salvar(pedidoAtual);
			return ResponseEntity.ok(pedidoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{pedidoId}")
	public ResponseEntity<Pedido> remover(@PathVariable("pedidoId") Long pedidoId) {
		try {
			cadastroPedido.excluir(pedidoId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
