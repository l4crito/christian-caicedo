package com.github.l4crito.wpgroup.service.impl;

import com.github.l4crito.wpgroup.entity.Message;
import com.github.l4crito.wpgroup.entity.User;
import com.github.l4crito.wpgroup.entity.UserGroup;
import com.github.l4crito.wpgroup.service.ChatService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ChatServiceImpl implements ChatService {
  private Set<Message> messages = new HashSet<>();
  private Set<UserGroup> userGroups = new HashSet<>();
  private Set<User> users = new HashSet<>();
  private Set<User> groups = new HashSet<>();

  @Override
  public Message sendMessage(Message message) throws Exception {
    verifyUser(message.getUserId());
    verifyGroup(message.getGroupId());
    verifyUserGroup(message.getUserId(), message.getGroupId());
    message.setDate(new Date());
    message.setId(messages.size());
    messages.add(message);
    return message;
  }

  @Override
  public void verifyGroup(String id) throws Exception {
    // Lanza una excepcion si el grupo no existe

    groups.stream()
        .filter(group -> group.getId().equals(id))
        .findFirst()
        .orElseThrow(Exception::new);
  }

  @Override
  public void verifyUser(String id) throws Exception {
    // Lanza una excepcion si el usuario no existe
    users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(Exception::new);
  }

  @Override
  public void verifyUserGroup(String userId, String groupId) throws Exception {

    // Lanza una excepcion si el usuario no pertenece al grupo, no necesariamente aplica a wp pero
    // es lo mas rapido
    userGroups.stream()
        .filter(
            userGroup ->
                userGroup.getUserId().equals(userId) && userGroup.getGroupId().equals(groupId))
        .findFirst()
        .orElseThrow(Exception::new);
  }
}
