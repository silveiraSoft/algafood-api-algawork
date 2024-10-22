package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	
	
	//public List<Restaurante> todos(){
	public List<Restaurante> listar(){
		return  manager.createQuery("from Restaurante", Restaurante.class).getResultList();
		
	}
	
	@Override
	//public Restaurante porId(Long id){
	public Restaurante buscar(Long id){
		return  manager.find(Restaurante.class,id);
	}
	
	
	@Transactional
	@Override
	//public Restaurante adicionar(Restaurante restaurante) {
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		//restaurante = porId(restaurante.getId());
		restaurante = buscar(restaurante.getId());
		manager.remove(restaurante);
	}

}
