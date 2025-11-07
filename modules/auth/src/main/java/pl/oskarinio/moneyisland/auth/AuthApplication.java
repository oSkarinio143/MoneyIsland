package pl.oskarinio.moneyisland.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"pl.oskarinio.moneyisland.auth",
		"pl.oskarinio.moneyisland.shared"
})

@EnableJpaRepositories(basePackages = {
		"pl.oskarinio.moneyisland.auth",
		"pl.oskarinio.moneyisland.shared"
})
@EntityScan(basePackages = {
		"pl.oskarinio.moneyisland.auth",
		"pl.oskarinio.moneyisland.shared"
})
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
