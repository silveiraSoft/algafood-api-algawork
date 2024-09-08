package com.adalbertosolf.domain.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

//@JsonInclude(include.non_null)
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DadosCep {
	private String cep;
	//@JsonIgnore
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	//@JsonIgnore
	private String siafi;
	public String saida() {
		return "{"
			    +"\"cep\":"+this.cep+","
				+"\"logradouro\":\""+ this.logradouro
				+ "\"}";
	}

}
