package tech.chilo.sa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("tech.chilo.sa.entites")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "tech.chilo.sa.repository")
public class SaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaBackendApplication.class, args);
	}

}
