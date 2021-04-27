package com.ssafy.checksource.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDTO {
    
    private String token;
    private String name; 
    private boolean flag;
    private Long depart;
    private Long job;
}

