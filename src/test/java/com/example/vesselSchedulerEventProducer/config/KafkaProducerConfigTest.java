package com.example.vesselSchedulerEventProducer.config;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = {"classpath:application.properties"})
class KafkaProducerConfigTest {

    @MockBean
    KafkaProducerConfig kafkaProducerConfig;

    @Qualifier("default_bean")
    @MockBean
    ProducerFactory<String,String> producerFactory;


    @Value("${spring.kafka.properties.bootstrap.servers}")
    String bootstrapServers;

    @BeforeEach
    void setUP(){
        this.kafkaProducerConfig = new KafkaProducerConfig(bootstrapServers);
    }

    @Test
    public void test_ProducerConfigs(){
        Map<String, Object> props = kafkaProducerConfig.producerConfigs();

        assertEquals("pkc-epwny.eastus.azure.confluent.cloud:9092",kafkaProducerConfig.getBootstrapServers());
        assertEquals("org.apache.kafka.common.serialization.StringSerializer",props.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
        assertEquals("org.apache.kafka.common.serialization.StringSerializer",props.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
    }

}