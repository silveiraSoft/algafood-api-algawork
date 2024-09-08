package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

//import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.repository.CozinhaRepository;
import com.algaworks.algafood.domain.model.repository.RestauranteRepository;

public class InclusaoRestauranteMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Restaurante Chines");
		restaurante1.setTaxaFrete(new BigDecimal(08.10));
		Cozinha cozinha = cozinhaRepository.buscar(1L);
		
		restaurante1.setCozinha(cozinha);
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Restaurante Americana");
		restaurante2.setTaxaFrete(new BigDecimal(9.10));
		Cozinha cozinha2 = cozinhaRepository.buscar(1L);
		restaurante2.setCozinha(cozinha2);
		
		restaurante1 = restauranteRepository.salvar(restaurante1);
		restaurante2 = restauranteRepository.salvar(restaurante2);
		
		System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());
		System.out.printf("%d - %s\n", restaurante2.getId(), restaurante2.getNome());
		
	}
}
