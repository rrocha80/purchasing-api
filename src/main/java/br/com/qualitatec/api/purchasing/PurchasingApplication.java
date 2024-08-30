package br.com.qualitatec.api.purchasing;

import br.com.qualitatec.api.purchasing.integration.IntegrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PurchasingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchasingApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(IntegrationService service) {
		return args -> {
			service.productIntegration();
			service.compraIntegration();
		};
	}

}
