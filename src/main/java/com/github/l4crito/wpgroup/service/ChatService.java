package com.github.l4crito.wpgroup.service;

import com.github.l4crito.wpgroup.entity.Group;
import com.github.l4crito.wpgroup.entity.Message;
import com.github.l4crito.wpgroup.entity.User;
import com.github.l4crito.wpgroup.entity.UserGroup;

import java.util.Set;

public interface ChatService {
    Message sendMessage(Message message) throws Exception;

    Group verifyGroup(Integer id) throws Exception;

    User verifyUser(Integer id) throws Exception;

    void verifyUserGroup(Integer userId,Integer groupId) throws Exception;

    User addUser(User user);

    void addContactToUSer(Integer userId, Integer contactId);

    Group addGroup(Group group);

    void joinGroup(UserGroup userGroup) throws Exception;

    Set<Message> getMessages(Integer userId,Integer groupId)throws Exception;


}
