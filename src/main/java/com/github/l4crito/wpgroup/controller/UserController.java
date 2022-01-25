package com.github.l4crito.wpgroup.controller;

import com.github.l4crito.wpgroup.entity.Group;
import com.github.l4crito.wpgroup.entity.User;
import com.github.l4crito.wpgroup.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/usuario")
@CrossOrigin()
public class UserController {
    @Autowired
    ChatService chatService;

    @PostMapping(value = "/crear")
    User create(@RequestBody User user) throws Exception {
        return chatService.addUser(user);
    }
}
