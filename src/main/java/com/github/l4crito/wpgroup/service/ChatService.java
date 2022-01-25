package com.github.l4crito.wpgroup.service;

import com.github.l4crito.wpgroup.entity.Group;
import com.github.l4crito.wpgroup.entity.Message;
import com.github.l4crito.wpgroup.entity.User;
import com.github.l4crito.wpgroup.entity.UserGroup;

import java.util.Set;

public interface ChatService {
    Message sendMessage(Message message) throws Exception;

    void verifyGroup(Integer id) throws Exception;

    void verifyUser(Integer id) throws Exception;

    void verifyUserGroup(Integer userId,Integer groupId) throws Exception;

    User addUser(User user);

    Group addGroup(Group group);

    void joinGroup(UserGroup userGroup) throws Exception;

    Set<Message> getMessages(Integer groupId) throws Exception;


}
