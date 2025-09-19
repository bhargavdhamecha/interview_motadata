package com.motadata.interview.service;

import com.motadata.interview.constant.OutputStatus;
import com.motadata.interview.entity.EventDetails;
import com.motadata.interview.entity.RawOperationalEvent;
import com.motadata.interview.repository.EventDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


import java.util.concurrent.CompletableFuture;

/**
 * Service responsible for publishing events to Kafka.
 */
@Slf4j
@Service
public class EventPublisher {

    private final KafkaTemplate<String, RawOperationalEvent> kafkaTemplate;
    private final EventDetailsRepository detailsRepository;

    @Autowired
    public EventPublisher(KafkaTemplate<String, RawOperationalEvent> kafkaTemplate, EventDetailsRepository detailsRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.detailsRepository = detailsRepository;
    }

    public void publishEvent(RawOperationalEvent event) {
        CompletableFuture<SendResult<String, RawOperationalEvent>> future = kafkaTemplate.send("raw-events", event);

        future.whenCompleteAsync((result, ex) -> {
            if (ex != null) {
                log.debug("Unable to send message = {} due to: {}", event.toString(), ex.getMessage());
                insertEventDetails(event, OutputStatus.FAILURE);
            } else {
                log.error("Message sent successfully with offset = {}", result.getRecordMetadata().offset());
                insertEventDetails(event, OutputStatus.PENDING);
            }
        });

    }

    private void insertEventDetails(RawOperationalEvent event, OutputStatus status) {
        // Implement the logic to insert event details into the database
        // You can use the detailsRepository to save the event details
        // Example:
         EventDetails eventDetails = new EventDetails();
         eventDetails.setCategory(event.getCategory());
         eventDetails.setSuggestedAction(event.getSuggestedAction());
         eventDetails.setQuery(event.getQuery());
         eventDetails.setOutputStatus(status);
         detailsRepository.save(eventDetails);
    }
}
