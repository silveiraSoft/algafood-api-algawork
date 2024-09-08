package com.adalbertosolf.domain.service;

//import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
/*
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
*/
//import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.adalbertosolf.domain.model.DadosCep;

import reactor.core.publisher.Mono;


@Service
public class CepService {

	@Autowired
	//private WebClient.Builder webClientBuilder;
	private WebClient webClient;
	
	/*
	public String getDadosCEP(String cep){
		String json;
		try {
			URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
	        URLConnection urlConnection = url.openConnection();
	        InputStream is = urlConnection.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        StringBuilder jsonSb = new StringBuilder();
	        br.lines().forEach(l -> {
		        	if(l.indexOf("siafi")==-1) {
		        		jsonSb.append(l.trim());
		        	}else {
		        		if(jsonSb.length()>0)
		        			jsonSb.setLength(jsonSb.length()-1);
		        	}
		        }
	        );
	        
	        json = jsonSb.toString();
	        return json;
        }catch (Exception e) {
            throw new RuntimeException(e);
	    }
	}
	*/
	/*
	public Object getDadosCEP(String cep){
		Object result =  webClientBuilder.build()
						.get()
						.uri("http://viacep.com.br/ws/"+ cep +"/json")
						.retrieve()
						.bodyToMono(Object.class)
						//.collectList()
						.block();
		 return result;
	}
	*/
	public DadosCep getDadosCEP(String cep){
	//public String getDadosCEP(String cep){
		Mono<DadosCep> result =  this.webClient
				//.get()
				.method(HttpMethod.GET)
				.uri("/ws/{cep}/json",cep)
				//.uri("/ws/"+cep+"/json")
				.retrieve()
				.bodyToMono(DadosCep.class);
				
		result.subscribe(p -> {
					//return 1;// "{cep:"+p.getCep()+"}";
					System.out.println("{'cep':"+p.getCep()+"}");
					//return 
				});
				
		
		//DadosCep dadosCep = result.block(); 
		
		DadosCep dadosCep = result.block(); 
		DadosCep dadosCep2 = new DadosCep(dadosCep.getCep(),dadosCep.getLogradouro(),null,null,null,null,null,null,null,null);
		return dadosCep2;
		
		/*
		DadosCep dadosCep = Mono.zip(result,result).map(tuple ->{
			DadosCep dadosCep2 = new DadosCep(tuple.getT1().getCep(),tuple.getT1().getLogradouro(),null,null,null,null,null,null,null,null);
			return dadosCep2;
		}).block();
		*/
		//return dadosCep.saida();
				
	}
	
	
}
