package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private Long job;
    private Long depart;
    private Integer userImg;
}
