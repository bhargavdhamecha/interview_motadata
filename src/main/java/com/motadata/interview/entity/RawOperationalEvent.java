package com.motadata.interview.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * Represents a MODEL(VO) raw operational event with its details.
 */
@Getter
@Setter
public class RawOperationalEvent {
    private LocalDateTime timestamp;
    private String category;
    private String suggestedAction;
    private String query;
}
