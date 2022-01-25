package com.github.l4crito.wpgroup.service;

import com.github.l4crito.wpgroup.entity.Group;
import com.github.l4crito.wpgroup.entity.Message;
import com.github.l4crito.wpgroup.entity.User;
import com.github.l4crito.wpgroup.entity.UserGroup;
import com.github.l4crito.wpgroup.service.impl.ChatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ChatServiceTest {
    @Autowired
    ChatServiceImpl chatService;

    @BeforeEach
    void clearMocks(){
        chatService.getMessages().clear();
        chatService.getUsers().clear();
        chatService.getUserGroups().clear();
        chatService.getGroups().clear();
        Assert.isTrue(chatService.getMessages().size()==0,"Should be empty");
        Assert.isTrue(chatService.getUsers().size()==0,"Should be empty");
        Assert.isTrue(chatService.getUserGroups().size()==0,"Should be empty");
        Assert.isTrue(chatService.getGroups().size()==0,"Should be empty");


    }

    @Test
    void shouldCreateUser(){
        String name="Test";
       User user= chatService.addUser(User.builder().name(name).build());
       Assert.notNull(user.getId(),"Should have an id");
       Assert.isTrue(chatService.getUsers().stream().filter(usr->usr.getName().equals(name)).count()>0,"Should have an id");
    }

    @Test
    void shouldCreateAGroup(){
        String name="group";
        Group group= chatService.addGroup(Group.builder().name(name).build());
        Assert.notNull(group.getId(),"Should have an id");
        Assert.isTrue(chatService.getGroups().stream().filter(gro->gro.getName().equals(name)).count()>0,"Should have an id");
    }
    @Test
    void shouldAddUserToGroup() throws Exception {
        Group group= Group.builder().name("grupo").id(10).build();
        User user= User.builder().name("Christian").id(10).build();
        UserGroup userGroup=UserGroup.builder().groupId(10).userId(10).build();
        chatService.getUsers().add(user);
        chatService.getGroups().add(group);
        chatService.joinGroup(userGroup);
        Assert.isTrue(chatService.getUserGroups().stream().filter(gro->gro.getUserId()==10&&gro.getGroupId()==10).count()>0,"");
    }

    @Test
    void shouldThrowExceptionIfUserNotExistAddingAUserGroup() throws Exception {
        Group group= Group.builder().name("grupo").id(10).build();
        User user= User.builder().name("Christian").id(10).build();
        UserGroup userGroup=UserGroup.builder().groupId(78).userId(10).build();
        chatService.getUsers().add(user);
        chatService.getGroups().add(group);
        assertThrows(ResponseStatusException.class, () -> {
            chatService.joinGroup(userGroup);
        });
    }

    @Test
    void shouldThrowExceptionIfGroupNotExistAddingAUserGroup()  {
        Group group= Group.builder().name("grupo").id(10).build();
        User user= User.builder().name("Christian").id(10).build();
        UserGroup userGroup=UserGroup.builder().groupId(10).userId(78).build();
        chatService.getUsers().add(user);
        chatService.getGroups().add(group);
        assertThrows(ResponseStatusException.class, () -> {
            chatService.joinGroup(userGroup);
        });
    }

    @Test
    void shouldSendAMessage()  {
        Group group= Group.builder().name("grupo").id(10).build();
        User user= User.builder().name("Christian").id(10).build();
        UserGroup userGroup=UserGroup.builder().groupId(10).userId(10).build();
        chatService.getUsers().add(user);
        chatService.getGroups().add(group);
        chatService.getUserGroups().add(userGroup);
        Message message =Message.builder().message("message").userId(10).groupId(10).build();
        chatService.sendMessage(message);
        assertNotNull(message.getId());
        Assert.isTrue(chatService.getMessages().stream().filter(msg->msg.getUserId()==10&&msg.getGroupId()==10).count()>0,"");

    }


}
