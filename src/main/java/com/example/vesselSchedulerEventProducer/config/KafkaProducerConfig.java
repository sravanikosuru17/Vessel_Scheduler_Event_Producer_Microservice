package com.example.vesselSchedulerEventProducer.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

//@Configuration
@AllArgsConstructor
@Getter
public class KafkaProducerConfig {

    @Value("${spring.kafka.properties.bootstrap.servers}")
    private String bootstrapServers;

	
	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}
	 
	
    @Bean
    public ProducerFactory<String, String> producerFactory() {
		/*
		 * return new DefaultKafkaProducerFactory<>(
		 * Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
		 * ProducerConfig.RETRIES_CONFIG, 0, ProducerConfig.BUFFER_MEMORY_CONFIG,
		 * 33554432, ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
		 * ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class) );
		 */
        
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    
    

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
