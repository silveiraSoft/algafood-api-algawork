package com.adalbertosolf.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.WebClient;

import com.adalbertosolf.domain.service.CepService;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//@JsonInclude(Include.NOT_NULL)
@RestController
@RequestMapping(value = "/api/consultacep")
public class CepController {
	@Autowired
	private CepService cepService;
	
	@GetMapping(value="/{cep}")
	public ResponseEntity<Object> consultaCEP(@PathVariable("cep") String cep) throws Exception {		
		
		try {
		
			Object dadosCEP = cepService.getDadosCEP(cep);
			
			//Thread.sleep(3000);
			if (dadosCEP != null) {
				return ResponseEntity.ok(dadosCEP);
			}
		
			return ResponseEntity.notFound().build();
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	/*
	public ResponseEntity<String> consultaCEP(@PathVariable("cep") String cep) {		
		
		try {
		
			String dadosCEP = cepService.getDadosCEP(cep);
			
			if (dadosCEP != null) {
				return ResponseEntity.ok(dadosCEP);
			}
		
			return ResponseEntity.notFound().build();
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	*/
	
}
