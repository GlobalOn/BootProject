package com.KrWeb.example.Repository;

import com.KrWeb.example.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
