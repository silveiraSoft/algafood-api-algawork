package com.adalbertosolf.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.adalbertosolf.domain.exception.EntidadeEmUsoException;
import com.adalbertosolf.domain.exception.EntidadeNaoEncontradaException;
import com.adalbertosolf.domain.model.ItemPedido;
import com.adalbertosolf.domain.repository.ItemPedidoRepository;
@Service
public class CadastroItemPedidoService {
	@Autowired
	private ItemPedidoRepository itempedidoRepository;
	
	public ItemPedido salvar(ItemPedido itempedido) {
		return itempedidoRepository.salvar(itempedido);
	}
	
	public void excluir(Long itempedidoId) {
		try {
			itempedidoRepository.remover(itempedidoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cozinha com código %d", itempedidoId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de código %d não pode ser removida, pois está em uso", itempedidoId));
		}
	}
}
