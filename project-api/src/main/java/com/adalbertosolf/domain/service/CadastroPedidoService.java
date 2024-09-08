package com.adalbertosolf.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.adalbertosolf.domain.exception.EntidadeEmUsoException;
import com.adalbertosolf.domain.exception.EntidadeNaoEncontradaException;
import com.adalbertosolf.domain.model.Pedido;
import com.adalbertosolf.domain.repository.PedidoRepository;
@Service
public class CadastroPedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.salvar(pedido);
	}
	
	public void excluir(Long pedidoId) {
		try {
			pedidoRepository.remover(pedidoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cozinha com código %d", pedidoId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de código %d não pode ser removida, pois está em uso", pedidoId));
		}
	}
}
