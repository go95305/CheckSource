package com.ssafy.checksource.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "github_user")
public class GithubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "github_id")
    private Long githubId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @Column(name = "user_github_id")
    private String userGithubId;

    private String username;
    @Column(name = "user_access_token")
    private String userAccessToken;
}
