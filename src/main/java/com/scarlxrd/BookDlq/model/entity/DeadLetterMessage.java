package com.scarlxrd.BookDlq.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;


@Document(collection = "dead_letter_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeadLetterMessage {

    @MongoId
    private String id;
    private String payload;
    private String reason; // guardar motivo
    private LocalDateTime receivedAt;
}
