package com.monnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

	/*
		   author: github@GuilhermeCoelhoFB

	   Main idea
		  Definir os dados a serem persistidos
		   • ID: auto-increment (think about)
		   • Nome : string
		   • Clima : string
		   • Terreno : string
		   • Para cada planeta também devemos ter a quantidade de aparições em filmes que
			 deve ser obtida pela api do Star Wars na inserção do planeta : Int

			 Funcionalidades desejadas:
			   • Adicionar um planeta (com nome, clima e terreno)
			   • Listar planetas do banco de dados
			   • Listar planetas da API do Star Wars
			   • Buscar por nome no banco de dados
			   • Buscar por ID no banco de dados
			   • Remover planeta
    */

@SpringBootApplication
public class SwapiApplication {


    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://swapi.dev/api/")
                .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .build();
    }

	public static void main(String[] args) {
		SpringApplication.run(SwapiApplication.class, args);
	}

}
