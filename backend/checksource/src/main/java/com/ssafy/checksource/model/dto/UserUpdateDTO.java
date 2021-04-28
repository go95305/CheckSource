package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String gitlabId;
    private Long job;
    private Long depart;
}
