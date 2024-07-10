package tech.cognity.almacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiInfraccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiInfraccionApplication.class, args);
	}

}
