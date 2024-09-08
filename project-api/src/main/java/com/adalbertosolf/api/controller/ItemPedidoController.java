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
import com.adalbertosolf.domain.model.ItemPedido;
import com.adalbertosolf.domain.repository.ItemPedidoRepository;
import com.adalbertosolf.domain.service.CadastroItemPedidoService;

@RestController
@RequestMapping("/itempedidos")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoRepository itempedidoRepository;
	@Autowired
	private CadastroItemPedidoService cadastroItemPedido;
	
	@GetMapping
	public List<ItemPedido> listar() {
		return itempedidoRepository.listar();
	}
	
	@GetMapping("/{itempedidoId}")
	public ResponseEntity<ItemPedido> buscar(@PathVariable("itempedidoId") Long itempedidoId) {
		ItemPedido itempedido = itempedidoRepository.buscar(itempedidoId);
		
		if (itempedido != null) {
			return ResponseEntity.ok(itempedido);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public ItemPedido adicionar(@RequestBody ItemPedido itempedido) {
		return itempedidoRepository.salvar(itempedido);
	}
	
	@PutMapping("/{itempedidoId}")
	public ResponseEntity<ItemPedido> atualizar(@PathVariable("itempedidoId") Long itempedidoId,
			@RequestBody ItemPedido itempedido) {
		ItemPedido itemPedidoAtual = itempedidoRepository.buscar(itempedidoId);
		if (itemPedidoAtual != null) {
			BeanUtils.copyProperties(itempedido, itemPedidoAtual, "id");
			itemPedidoAtual = cadastroItemPedido.salvar(itemPedidoAtual);
			return ResponseEntity.ok(itemPedidoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{itempedidoId}")
	public ResponseEntity<ItemPedido> remover(@PathVariable("clienteId") Long itempedidoId) {
		try {
			cadastroItemPedido.excluir(itempedidoId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
