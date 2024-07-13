package ir.ac.kntu.db_project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableCaching
@EnableWebSecurity
public class DbProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbProjectBackendApplication.class, args);
	}

}
