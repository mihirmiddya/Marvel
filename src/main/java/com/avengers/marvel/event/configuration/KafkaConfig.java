package com.avengers.marvel.event.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.avengers.marvel.event.service.KafkaConsumer.KAFKA_TOPIC;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(KAFKA_TOPIC).build();
    }
}
