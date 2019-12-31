package io.edelivery.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class EDeliveryWebhookApplication {

	public static void main(String[] args) {
		SpringApplication.run(EDeliveryWebhookApplication.class, args);
	}

}
