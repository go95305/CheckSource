package com.ssafy.checksource.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Tree {
    private String path;
    private String sha;
}
