package com.example.boilerroom.controller;
import com.example.boilerroom.entity.Message;
import com.example.boilerroom.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return service.create(message.getText(), message.getNumber());
    }

    @GetMapping
    public List<Message> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}/number")
    public int getNumber(@PathVariable Long id) {
        return service.getNumber(id);
    }

    @GetMapping("/{id}/text")
    public String getText(@PathVariable Long id) {
        return service.getText(id);
    }


}
