package com.github.l4crito.wpgroup.service;

import com.github.l4crito.wpgroup.entity.Message;

public interface ChatService {
    Message sendMessage(Message message) throws Exception;

    void verifyGroup(String id) throws Exception;

    void verifyUser(String id) throws Exception;

    void verifyUserGroup(String userId,String groupId) throws Exception;


}
