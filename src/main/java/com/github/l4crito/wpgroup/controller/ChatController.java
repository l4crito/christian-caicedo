package com.github.l4crito.wpgroup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.l4crito.wpgroup.entity.Message;
import com.github.l4crito.wpgroup.service.ChatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequestMapping("/chat")
@CrossOrigin()
public class ChatController {

    @Autowired
    WebSocketController webSocketController;


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ChatService chatService;

    @PostMapping(value = "/enviar")
    Message sendMessage(@RequestBody Message message) throws Exception {
        Message message1=chatService.sendMessage(message);
        webSocketController.sendMessage(objectMapper.writeValueAsString(message1));
        return message1;
    }

    @GetMapping(value = "/mensajes")
    Set<Message> getMessages(@RequestParam(name = "grupo") Integer groupId,@RequestParam(name = "usuario") Integer userId) throws Exception {
        return chatService.getMessages(userId,groupId);
    }
}
