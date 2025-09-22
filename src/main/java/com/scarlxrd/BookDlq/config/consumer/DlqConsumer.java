package com.scarlxrd.BookDlq.config.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scarlxrd.BookDlq.config.rabbitmq.RabbitMQDlqConfig;
import com.scarlxrd.BookDlq.model.entity.ClientRequestDTO;
import com.scarlxrd.BookDlq.model.entity.DeadLetterMessage;
import com.scarlxrd.BookDlq.model.repository.DeadLetterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class DlqConsumer {

    private final DeadLetterRepository repository;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(DlqConsumer.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public DlqConsumer(DeadLetterRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQDlqConfig.DLQ_NAME)
    public void consume(ClientRequestDTO clientRequestDTO) {
        String payload = "";
        try {
            // Converte para JSON com pretty print
            payload = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(clientRequestDTO);

            // Persistência no banco
            DeadLetterMessage deadLetter = DeadLetterMessage.builder()
                    .payload(payload)
                    .reason("Processamento falhou no book-service-api")
                    .receivedAt(LocalDateTime.now())
                    .build();
            repository.save(deadLetter);


            Map<String, Object> logMap = Map.of(
                    "event", "dlq_message",
                    "payload", clientRequestDTO,
                    "status", "SUCCESS",
                    "timestamp", LocalDateTime.now().format(formatter)
            );
            String logPretty = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(logMap);
            log.info("\n{}", logPretty);

        } catch (Exception e) {
            Map<String, Object> logMap = Map.of(
                    "event", "dlq_message",
                    "payload", payload,
                    "status", "FAIL",
                    "error", e.getMessage(),
                    "timestamp", LocalDateTime.now().format(formatter)
            );
            try {
                String logPretty = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(logMap);
                log.error("\n{}", logPretty, e);
            } catch (Exception ex) {
                log.error("Erro ao serializar log da DLQ", ex);
            }
        }
    }
}
