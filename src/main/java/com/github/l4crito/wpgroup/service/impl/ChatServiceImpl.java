package com.github.l4crito.wpgroup.service.impl;

import com.github.l4crito.wpgroup.entity.Group;
import com.github.l4crito.wpgroup.entity.Message;
import com.github.l4crito.wpgroup.entity.User;
import com.github.l4crito.wpgroup.entity.UserGroup;
import com.github.l4crito.wpgroup.service.ChatService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
public class ChatServiceImpl implements ChatService {
  private List<Message> messages = new ArrayList<>();
  private Set<UserGroup> userGroups = new HashSet<>();
  private Set<User> users = new HashSet<>();
  private Set<Group> groups = new HashSet<>();



  ChatServiceImpl() {
    User user= User.builder().name("Christian Caicedo").status("Realizando La prueba").build();
    User user2= User.builder().name("Usuario contacto").status("").build();
    User user3= User.builder().name("Desconocido").status("").build();
    addUser(user);
    addUser(user2);
    addUser(user3);

    Group group= Group.builder().name("Primer grupo").build();
    addGroup(group);

    UserGroup userGroup= UserGroup.builder().groupId(0).userId(0).build();
    UserGroup userGroup2= UserGroup.builder().groupId(0).userId(1).build();
    UserGroup userGroup3= UserGroup.builder().groupId(0).userId(2).build();
    joinGroup(userGroup);
    joinGroup(userGroup2);
    joinGroup(userGroup3);

    addContactToUSer(0,1);

    Message message= Message.builder().groupId(0).userId(0).message("Mi primer mensaje").build();
    Message message2= Message.builder().groupId(0).userId(1).message("Mensaje de un conocido").build();
    Message message3= Message.builder().groupId(0).userId(2).message("Mensaje de un desconocido").build();
    sendMessage(message);
    sendMessage(message2);
    sendMessage(message3);

  }

  @Override
  public Message sendMessage(Message message) {

    verifyUser(message.getUserId());
    verifyGroup(message.getGroupId());
    verifyUserGroup(message.getUserId(), message.getGroupId());
    message.setDate(new Date());
    message.setId(messages.size());
    messages.add(message);
    return message;
  }

  @Override
  public Group verifyGroup(Integer id) throws ResponseStatusException {
   return groups.stream()
        .filter(group -> group.getId().equals(id))
        .findFirst()
            .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
  }

  @Override
  public User verifyUser(Integer id) throws ResponseStatusException {
    return  users.stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
  }

  @Override
  public void verifyUserGroup(Integer userId, Integer groupId) throws ResponseStatusException {
    userGroups.stream()
        .filter(
            userGroup ->
                userGroup.getUserId().equals(userId) && userGroup.getGroupId().equals(groupId))
        .findFirst()
            .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
  }

  @Override
  public User addUser(User user) {
    Integer id=users.size();
    user.setId(id);
    user.getContacts().add(id);
    users.add(user);
    return user;
  }

  @Override
  public Group addGroup(Group group) {
    group.setId(groups.size());
    groups.add(group);
    return group;
  }

  @Override
  public void joinGroup(UserGroup userGroup) throws ResponseStatusException {
    verifyUser(userGroup.getUserId());
    verifyGroup(userGroup.getGroupId());
    userGroups.add(userGroup);
  }

  @Override
  public Set<Message> getMessages(Integer userId,Integer groupId) throws ResponseStatusException {
    verifyGroup(groupId);
    User user=verifyUser(userId);
    verifyUserGroup(userId,groupId);
    Set<Message> groupMessages=messages.stream().filter(message->message.getGroupId().equals(groupId)).collect(Collectors.toSet());
    groupMessages.forEach(message -> {
      message.setContact(user.getContacts().contains(message.getUserId()));
    });
    return groupMessages ;
  }
  @Override
  public void addContactToUSer(Integer userId, Integer contactId){
    User user= verifyUser(userId);
    verifyUser(contactId);
    user.getContacts().add(contactId);
  }
}
