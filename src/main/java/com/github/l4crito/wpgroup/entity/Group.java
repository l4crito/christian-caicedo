package com.github.l4crito.wpgroup.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSerialize
@JsonDeserialize
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    Integer id;
    private String name;
}
