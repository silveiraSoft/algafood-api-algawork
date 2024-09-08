package com.adalbertosolf.infrastruture;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.adalbertosolf.domain.model.Pedido;
import com.adalbertosolf.domain.repository.PedidoRepository;
@Component
public class PedidoRepositoryImpl implements PedidoRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Pedido> listar() {
		
		return manager.createQuery("from Pedido", Pedido.class)
				.getResultList();
		
		
				
	}
	
	@Override
	public Pedido buscar(Long id) {
		return manager.find(Pedido.class, id);
	}
	
	@Transactional
	@Override
	public Pedido salvar(Pedido pedido) {
		return manager.merge(pedido);
	}
	
	@Transactional
	@Override
	public void remover(Long pedidoID) {
		Pedido pedido = buscar(pedidoID);
		
		if (pedido == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(pedido);
	}

}
