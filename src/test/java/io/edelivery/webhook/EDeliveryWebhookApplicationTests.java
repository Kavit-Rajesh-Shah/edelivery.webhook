package io.edelivery.webhook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.edelivery.webhook.services.RabbitMQSender;

@SpringBootTest
@ActiveProfiles("test")
class EDeliveryWebhookApplicationTests {

	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	@Test
	void contextLoads() {
	}
}
