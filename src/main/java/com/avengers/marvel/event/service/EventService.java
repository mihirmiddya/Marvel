package com.avengers.marvel.event.service;

import com.avengers.marvel.event.handler.EventHandler;
import com.avengers.marvel.event.model.Event;
import com.avengers.marvel.event.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private EventRepository eventRepository;

    public void trigger(int eventId) {
        try {
            Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event not found"));
            applicationContext.getBeansOfType(EventHandler.class).values()
                    .stream()
                    .filter(handler -> handler.isSupport(event.getType()))
                    .forEach(handler -> handler.trigger(event));
        } catch (NoSuchElementException ex) {
            logger.error("Event not found with id: {}", eventId, ex);
        } catch (Exception ex) {
            logger.error("Error while triggering event.", ex);
        }
    }

}
