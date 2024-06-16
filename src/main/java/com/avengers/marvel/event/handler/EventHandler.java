package com.avengers.marvel.event.handler;

import com.avengers.marvel.event.model.Event;

public interface EventHandler {
    boolean isSupport(String type);
    void trigger(Event event);
}
