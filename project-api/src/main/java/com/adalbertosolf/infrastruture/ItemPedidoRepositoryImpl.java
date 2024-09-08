package com.adalbertosolf.infrastruture;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.adalbertosolf.domain.model.ItemPedido;
import com.adalbertosolf.domain.repository.ItemPedidoRepository;
@Component
public class ItemPedidoRepositoryImpl implements ItemPedidoRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ItemPedido> listar() {
		/*
		return manager.createQuery("from ItemPedido", ItemPedido.class)
				.getResultList();
		*/
	
		 return manager.createQuery("from ItemPedido p join fetch p.pedido", ItemPedido.class)
				.getResultList();
		
	}
	
	@Override
	public ItemPedido buscar(Long id) {
		return manager.find(ItemPedido.class, id);
	}
	
	@Transactional
	@Override
	public ItemPedido salvar(ItemPedido itempedido) {
		return manager.merge(itempedido);
	}
	
	@Transactional
	@Override
	public void remover(Long itempedidoID) {
        ItemPedido itempedido = buscar(itempedidoID);
		
		if (itempedido == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(itempedido);
	}

}
