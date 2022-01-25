package com.github.l4crito.wpgroup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.l4crito.wpgroup.entity.Message;
import com.github.l4crito.wpgroup.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequestMapping("/chat")
@CrossOrigin()
public class ChatController {
    @Autowired
    ChatService chatService;

    @PostMapping(value = "/enviar")
    Message sendMessage(@RequestBody Message message) throws Exception {
        return chatService.sendMessage(message);
    }

    @GetMapping(value = "/mensajes")
    Set<Message> getMessages(@RequestParam Integer grupo) throws Exception {
        return chatService.getMessages(grupo);
    }
}
