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
    public Message create() {
        return service.create("Hej, YH", 42);
    }

    @GetMapping
    public List<Message> getAll() {
        return service.getAll();
    }


}
