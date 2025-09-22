package com.scarlxrd.BookDlq.model.repository;

import com.scarlxrd.BookDlq.model.entity.DeadLetterMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeadLetterRepository extends JpaRepository<DeadLetterMessage, UUID> {
}
