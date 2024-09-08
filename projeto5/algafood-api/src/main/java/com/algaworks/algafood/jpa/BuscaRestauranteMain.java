package com.algaworks.algafood.jpa;

//import java.text.DecimalFormat;

//import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.repository.RestauranteRepository;

public class BuscaRestauranteMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = restauranteRepository.buscar(1L);
		
		/*
		DecimalFormat formatter = new DecimalFormat("#.00");
		
		String taxaFreteFormatado = formatter.format(restaurante.getTaxaFrete());
		
		System.out.printf("O restaurante: %s, tem taxa de frete igual a: %s %n",restaurante.getNome(),taxaFreteFormatado);
		//System.out.println(restaurante.getNome());
		
		*/
		//System.out.printf("O restaurante: %s, tem taxa de frete igual a: %f\n",restaurante.getNome(),restaurante.getTaxaFrete());
		System.out.printf("O restaurante: %s\n",restaurante.getNome());
		
	}
}
