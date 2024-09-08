package com.adalbertosolf.infrastruture;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.adalbertosolf.domain.model.Cliente;
import com.adalbertosolf.domain.repository.ClienteRepository;
@Component
public class ClienteRepositoryImpl implements ClienteRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cliente> listar() {
		return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
	}
	
	@Override
	public Cliente buscar(Long id) {
		return manager.find(Cliente.class, id);
	}
	
	@Transactional
	@Override
	public Cliente salvar(Cliente cliente) {
		return manager.merge(cliente);
	}
	
	@Transactional
	@Override
	public void remover(Long clienteID) {
        Cliente cliente = buscar(clienteID);
		
		if (cliente == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cliente);
	}

}
