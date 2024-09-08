package com.adalbertosolf.domain.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.adalbertosolf.domain.model.ItemPedido;
/*
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
*/
public interface ItemPedidoRepository{
	List<ItemPedido> listar();
	ItemPedido buscar(Long id);
	ItemPedido salvar(ItemPedido itemPedido);
	void remover(Long itemPedidoID);
}
