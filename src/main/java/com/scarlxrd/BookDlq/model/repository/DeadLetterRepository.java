package com.scarlxrd.BookDlq.model.repository;

import com.scarlxrd.BookDlq.model.entity.DeadLetterMessage;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface DeadLetterRepository extends MongoRepository<DeadLetterMessage, String> {
}
