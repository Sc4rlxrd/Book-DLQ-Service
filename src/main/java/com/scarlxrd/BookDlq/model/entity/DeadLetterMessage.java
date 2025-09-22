package com.scarlxrd.BookDlq.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dead_letter_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeadLetterMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;
    @Column(nullable = false)
    private String reason; // guardar motivo
    @Column(nullable = false)
    private LocalDateTime receivedAt;
}
