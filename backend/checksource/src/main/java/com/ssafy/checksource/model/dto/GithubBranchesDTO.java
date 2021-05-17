package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GithubBranchesDTO {
    private String name;
    private Commit commit;
}
