package com.github.l4crito.wpgroup.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize
@JsonDeserialize
@Builder
public class Message {
    private Integer id;
    private Integer userId;
    private Integer groupId;
    private String message;
    private Date date;
}
