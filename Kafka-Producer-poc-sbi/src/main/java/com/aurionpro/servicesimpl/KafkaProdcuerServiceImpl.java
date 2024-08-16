package com.aurionpro.servicesimpl;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.aurionpro.services.KafkaProducerService;
import com.aurionpro.util.UniqueNumberGenerator;

@Service
public class KafkaProdcuerServiceImpl implements KafkaProducerService {

	@Autowired
	private KafkaTemplate temp;

	/**
	 *
	 */
	@Override
	public String sendMessageToTopic(String msg) {
		// TODO Auto-generated method stub

		CompletableFuture<SendResult<Long, Object>> send = temp.send("my-topic",
				UniqueNumberGenerator.generateUniqueNumber(), msg);

		send.whenComplete((result, ex) -> {

			if (ex == null) {

				System.out
						.println(("Sent message [ " + msg + " ] with offset [ " + result.getRecordMetadata().offset()));
			} else {

				System.out.println("Unable to send the message " + msg);

			}
		});

		

		return send.toString();

	}

}
