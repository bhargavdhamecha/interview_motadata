package com.motadata.interview.entity;

import com.motadata.interview.constant.OutputStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_master")
@Entity
public class EventDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;

    private LocalDateTime publisherTimestamp;

    private LocalDateTime consumerTimestamp;

    private String category;

    private String suggestedAction;

    private String query;

    @Lob
    private String generatedInsight;

    @Enumerated(EnumType.STRING)
    private OutputStatus outputStatus;
}
