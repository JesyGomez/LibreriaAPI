package com.proyecto.libreria;

import com.proyecto.libreria.principal.Principal;
import com.proyecto.libreria.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(LibroRepository repository) {
		return (args) -> {
			Principal principal = new Principal(repository);
			principal.muestraElMenu();
		};
	}
}
