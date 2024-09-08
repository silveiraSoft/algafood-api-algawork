package com.algaworks.algafood.jpa;

//import java.text.DecimalFormat;
//import java.text.NumberFormat;
import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.repository.RestauranteRepository;

public class ConsultaRestauranteMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		RestauranteRepository cozinhaRepository = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> restaurantes = cozinhaRepository.listar();
		
		for(Restaurante restaurante: restaurantes) {
			//System.out.println(restaurante.getNome());
			//NumberFormat nf = NumberFormat.getInstance();
			//nf.setMaximumFractionDigits(3);//seto o máximo de casas decimais para 2
			//String taxaFreteFormatado = nf.format(restaurante.getTaxaFrete());
			
			/*
			DecimalFormat formatter = new DecimalFormat("#.00");
			
			String taxaFreteFormatado = formatter.format(restaurante.getTaxaFrete());
			
			System.out.printf("O restaurante: %s, tem taxa de frete igual a: %s %n",restaurante.getNome(),taxaFreteFormatado);
			*/
			System.out.printf("%s - %f - %s\n", restaurante.getNome(),
			restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
			
		}
		
	}
}
