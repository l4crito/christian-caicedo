package com.github.l4crito.wpgroup.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

@Data
@JsonSerialize
@JsonDeserialize
@Builder
public class UserGroup {
    Integer userId;
    Integer groupId;
}
