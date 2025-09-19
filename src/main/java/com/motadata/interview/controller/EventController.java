package com.motadata.interview.controller;

import com.motadata.interview.entity.RawOperationalEvent;
import com.motadata.interview.service.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EventController {

    private final EventPublisher eventPublisher;

    public EventController(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/event/search-query")
    public String receiveRawEvent(@RequestBody RawOperationalEvent event) {
        // Process the raw event (log it, store it, analyze it, etc.)
        log.debug("Received Event: {} from {}", event.getCategory(), event.getSuggestedAction());

        // (Optional) Call an AI module here to analyze the event
        eventPublisher.publishEvent(event);
        return "Event received successfully!";
    }
}
