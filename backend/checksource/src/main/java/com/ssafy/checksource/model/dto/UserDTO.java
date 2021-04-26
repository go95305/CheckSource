package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String name;
    private String gitLabId;
    private Long jobId;
    private Long departId;
}
