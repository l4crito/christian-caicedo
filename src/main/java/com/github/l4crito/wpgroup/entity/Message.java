package com.github.l4crito.wpgroup.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize
@JsonDeserialize
public class Chat {
    private String userId;
    private String groupId;
    private String message;
    private Date date;
}
