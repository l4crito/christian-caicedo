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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class ChatServiceImpl implements ChatService {
  private Set<Message> messages = new HashSet<>();
  private Set<UserGroup> userGroups = new HashSet<>();
  private Set<User> users = new HashSet<>();
  private Set<Group> groups = new HashSet<>();

  ChatServiceImpl() throws Exception {

    User user= User.builder().name("Christian Caicedo").status("Realizando La prueba").build();
    Group group= Group.builder().name("Primer grupo").build();
    UserGroup userGroup= UserGroup.builder().groupId(0).userId(0).build();
    Message message= Message.builder().groupId(0).userId(0).message("Mi primer mensaje").build();
    addUser(user);
    addGroup(group);
    joinGroup(userGroup);
    sendMessage(message);
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
  public void verifyGroup(Integer id) throws ResponseStatusException {
    // Lanza una excepcion si el grupo no existe

    groups.stream()
        .filter(group -> group.getId().equals(id))
        .findFirst()
            .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
  }

  @Override
  public void verifyUser(Integer id) throws ResponseStatusException {
    // Lanza una excepcion si el usuario no existe
    users.stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
  }

  @Override
  public void verifyUserGroup(Integer userId, Integer groupId) throws ResponseStatusException {

    // Lanza una excepcion si el usuario no pertenece al grupo, no necesariamente aplica a wp pero
    // es lo mas rapido
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
    user.setId(users.size());
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
  public Set<Message> getMessages(Integer groupId) throws ResponseStatusException {
    verifyGroup(groupId);
    return messages.stream().filter(message->message.getGroupId().equals(groupId)).collect(Collectors.toSet());
  }
}
