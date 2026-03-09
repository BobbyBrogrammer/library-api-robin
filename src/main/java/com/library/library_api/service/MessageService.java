package com.library.library_api.service;
import com.library.library_api.entity.Message;
import com.library.library_api.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message create(String text) {
        Message message = new Message(text);
        return repository.save(message);
    }

    public List<Message> getAll() {
        return repository.findAll();
    }
}
