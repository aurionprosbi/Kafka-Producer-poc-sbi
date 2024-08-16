package com.aurionpro.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

	@Bean
	public Map<String, Object> producerConfig() {

		Map<String, Object> map = new HashMap<>();
		map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"cms-cluster-kafka-plain-bootstrap-icashpro-sbi.apps.cmp-icashpro.asl.mum.sst:443");

		map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
		map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		// SSL Configuration
		map.put("security.protocol", "SSL");
		map.put("ssl.truststore.location",
				"C:\\Users\\abhishek.talakeri\\Documents\\kafka\\Kafka-Producer-poc-new openshift\\sbi.jks");
		map.put("ssl.truststore.password", "aurionpro");

		return map;
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {

		return new DefaultKafkaProducerFactory<>(producerConfig());

	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {

		return new KafkaTemplate<>(producerFactory());
	}

}
