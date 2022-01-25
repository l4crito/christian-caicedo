package com.github.l4crito.wpgroup.controller;

import com.github.l4crito.wpgroup.entity.Group;

import com.github.l4crito.wpgroup.entity.UserGroup;
import com.github.l4crito.wpgroup.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/grupo")
@CrossOrigin()
public class GroupController {
    @Autowired
    ChatService chatService;

    @PostMapping(value = "/crear")
    Group create(@RequestBody Group group) throws Exception {
        return chatService.addGroup(group);
    }


    @PostMapping(value = "/unirse")
    void join(@RequestBody UserGroup userGroup) throws Exception {
         chatService.joinGroup(userGroup);
    }
}
