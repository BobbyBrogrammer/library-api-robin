package com.library.library_api.controller;
import com.library.library_api.entity.Message;
import com.library.library_api.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public Message create() {
        return service.create("Foo");
    }

    @GetMapping
    public List<Message> getAll() {
        return service.getAll();
    }


}
