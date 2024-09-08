package com.adalbertosolf.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.adalbertosolf.domain.exception.EntidadeEmUsoException;
import com.adalbertosolf.domain.exception.EntidadeNaoEncontradaException;
import com.adalbertosolf.domain.model.Cliente;
import com.adalbertosolf.domain.repository.ClienteRepository;
@Service
public class CadastroClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente Cliente) {
		return clienteRepository.salvar(Cliente);
	}
	
	public void excluir(Long clienteId) {
		try {
			clienteRepository.remover(clienteId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cozinha com código %d", clienteId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de código %d não pode ser removida, pois está em uso", clienteId));
		}
	}
}
