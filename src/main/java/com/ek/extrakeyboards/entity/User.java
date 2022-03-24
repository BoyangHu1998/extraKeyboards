package com.ek.extrakeyboards.entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    private String userId;

    private String name;

    private String password;

    private String email;

    private String phoneNumber;

    private String resumeUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Job> jobs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<JobApplication> jobApplications;

    @ManyToMany
    private Set<Skill> skills;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
