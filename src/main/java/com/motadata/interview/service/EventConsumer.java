package com.motadata.interview.service;

import com.motadata.interview.entity.RawOperationalEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service to consume events from Kafka, process them using AI, and handle insights.
 */
@Slf4j
@Service
public class EventConsumer {

    private final AiService aiService;

    public EventConsumer(AiService aiService) {
        this.aiService = aiService;
    }

    /**
     * Listens to the "raw-events" topic and processes incoming events.
     * @param event The raw operational event received from Kafka.
     */
    @KafkaListener(topics = "raw-events", groupId = "event-consumer-group")
    public void consumeEvent(RawOperationalEvent event) {
        log.debug("Received event: " + event);

        // Generate AI-driven insight based on the query
        String insight = aiService.generateInsight(event.getQuery());

        // Log or process the insight further (e.g., store in DB, send back to UI)
        log.debug("Generated Insight: " + insight);
    }

    /**
     * on generating insight update the database with the insight
     * @param event
     * @param insight
     */
    private void updateDatabaseWithInsight(RawOperationalEvent event, String insight) {
        // Implement database update logic here


    }
}
