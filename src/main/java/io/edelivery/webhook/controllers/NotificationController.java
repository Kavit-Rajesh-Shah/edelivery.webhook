package io.edelivery.webhook.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.edelivery.webhook.domain.Notification;
import io.edelivery.webhook.services.RabbitMQSender;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
    @Value("${edelivery.http.auth-token-header-name}")
    private String principalRequestHeader;
	
	@ResponseBody
	@PostMapping
	@ApiImplicitParams(
			  @ApiImplicitParam(name = "x-edelivery-api-key", value = "x-edelivery-api-key", required = true, allowEmptyValue = false, paramType = "header", example = "Api Key"))
	public ResponseEntity<HttpStatus> post(@RequestBody Notification notification) {
		
		log.info("Notification received at:-" + new Date());

		//send the message to the RabbitMQ
		rabbitMQSender.send(notification);
		
		return new ResponseEntity(HttpStatus.OK);
	}
}
