package com.adalbertosolf.domain.repository;
import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.adalbertosolf.domain.model.Pedido;

public interface PedidoRepository{
	List<Pedido> listar();
	Pedido buscar(Long id);
	Pedido salvar(Pedido pedido);
	void remover(Long pedidoID);
}
