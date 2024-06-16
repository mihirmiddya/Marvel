package com.avengers.marvel.event.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private EventService eventService;

    private static final String KAFKA_GROUP = "mihir_group";
    public static final String KAFKA_TOPIC = "mihir_topic";

    @KafkaListener(topics = KAFKA_TOPIC, groupId = KAFKA_GROUP)
    public void consume(String eventId) {
        logger.info("Consumed event with id: {}", eventId);
        eventService.trigger(Integer.parseInt(eventId));
    }
}

