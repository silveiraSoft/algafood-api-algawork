package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import javax.persistence.Table;

/*@Getter
@Setter
@EqualsAndHashCode
*/
/*
 * @Data e @EqualsAndHashCode são do Lombok, para que o lombok funcione no projeto debe 
 * ser inserida a extensão do lombok no IDE e debe ser importado o plugin do lombok no arquivo pom do projeto. 
 * 
 * */
@Data  
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@Table(name="tab_cozinha")
public class Cozinha {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(/*name="nm_cozinha",*/ length=30, nullable = false)
	private String nome;
	
	/*
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cozinha other = (Cozinha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	*/
}
