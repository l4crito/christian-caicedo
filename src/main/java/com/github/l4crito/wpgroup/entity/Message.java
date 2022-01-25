package com.github.l4crito.wpgroup.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonSerialize
@JsonDeserialize
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer id;
    private Integer userId;
    private Integer groupId;
    private String message;
    private Date date;
    private boolean contact;
}
