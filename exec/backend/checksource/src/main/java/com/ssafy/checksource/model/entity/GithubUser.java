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
    @JoinColumn(name = "user_id")
    private User user;

    private String username;
    @Column(name = "personal_access_token")
    private String personalAccessToken;
}
