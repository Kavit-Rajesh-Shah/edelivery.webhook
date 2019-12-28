package io.edelivery.webhook.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.edelivery.webhook.domain.Notification;
import io.edelivery.webhook.services.RabbitMQSender;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

	@Value("${secret}")
	private String secret;

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@ResponseBody
	@PostMapping
	public ResponseEntity<HttpStatus> post(@RequestParam(name="secret") String secret, @RequestBody Notification notification) {
		
		log.info("Notification received at:-" + new Date());
		if (!secret.equals(this.secret))
        {
			return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

		//send the message to the RabbitMQ
		rabbitMQSender.send(notification);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
}
