package com.adalbertosolf.domain.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.adalbertosolf.domain.model.Cliente;
/*
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
*/
public interface ClienteRepository{
	List<Cliente> listar();
	Cliente buscar(Long id);
	Cliente salvar(Cliente cliente);
	void remover(Long clienteID);
}
