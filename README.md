# Book-DLQ-Service

## Descrição

O **Book-DLQ-Service** é um serviço Spring Boot responsável por consumir mensagens enviadas para a **Dead Letter Queue (DLQ)** da API principal de books, persistindo essas mensagens em um banco de dados e registrando logs detalhados sobre o processamento.

O serviço utiliza RabbitMQ como broker de mensagens e H2 como banco de dados em memória para persistência das mensagens da DLQ.

---

## Tecnologias

- Java 21
- Spring Boot 3.5.6
- Spring AMQP (RabbitMQ)
- Spring Data JPA
- H2 Database (runtime)
- Lombok
- Maven

---

## Funcionalidades

- Consumir mensagens da fila de DLQ (`book-dlq`) configurada no RabbitMQ.
- Persistir mensagens com falha no banco de dados H2.
- Logar mensagens processadas, incluindo status de sucesso ou falha.
- Serializar payloads em JSON formatado para fácil leitura.



