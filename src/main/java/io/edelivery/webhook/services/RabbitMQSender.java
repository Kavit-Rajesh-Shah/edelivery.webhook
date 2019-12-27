package io.edelivery.webhook.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.edelivery.webhook.domain.Notification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${edelivery.rabbitmq.exchange}")
	String exchange;

	@Value("${edelivery.rabbitmq.routingkey}")
	private String routingkey;
	
	public void send(Notification notification) {
		rabbitTemplate.convertAndSend(exchange, routingkey, notification);
		log.info("Send msg = " + notification);
	    
	}
}
