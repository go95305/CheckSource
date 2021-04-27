package com.ssafy.checksource.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TokenResultDTO {
    @ApiModelProperty(value="token")
    private String token;

    @ApiModelProperty(value="name")
    private String name;

    @ApiModelProperty(value="code")
    private Integer code;
}

