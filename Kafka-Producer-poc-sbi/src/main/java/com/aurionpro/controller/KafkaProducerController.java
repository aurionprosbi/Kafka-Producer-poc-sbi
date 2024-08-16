package com.aurionpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.services.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

	@Autowired
	private KafkaProducerService service;

	@GetMapping("/produce")
	public ResponseEntity<String> sendMessage(@RequestParam("msg") String msg) {

		String response = service.sendMessageToTopic(msg);
		return ResponseEntity.ok(response);
		
	}

}
