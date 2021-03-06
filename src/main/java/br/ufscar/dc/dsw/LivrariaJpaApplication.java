package br.ufscar.dc.dsw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;

import br.ufscar.dc.dsw.dao.ILivroDAO;
import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Livro;

@SpringBootApplication
public class LivrariaJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(LivrariaJpaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaJpaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ILivroDAO dao) {
		return (args) -> {
	
			// Recupere todos livros
			
			log.info("Livros recuperados -- findAll():");
			log.info("--------------------------------");
			for (Livro livro : dao.findAll()) {
				log.info(livro.toString());
			}
			log.info("");
	
			// Recupere um livro por seu ID
			
			Livro livro = dao.findById(1L);
			log.info("Livro recuperado -- findById(1L):");
			log.info("---------------------------------");
			log.info(livro.toString());
			log.info("");
			
			//insere 
			Editora editora = new Editora("55.789.390/0008-99", "Companhia das Letras");
			Livro liv = new Livro("A Revolução dos Bichos", "George Orwell", 2007, BigDecimal.valueOf(23.9), editora);
			dao.save(liv);
			log.info("Livro inserido com sucesso");
			log.info(liv.toString());
			
		};
	}
}