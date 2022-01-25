package com.github.l4crito.wpgroup.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@JsonSerialize
@JsonDeserialize
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String status;
    private String photo;
    @Builder.Default
    private Set<Integer> contacts = new HashSet<>() ;
}
