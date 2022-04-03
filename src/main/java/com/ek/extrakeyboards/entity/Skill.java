package com.ek.extrakeyboards.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "skill")
public class Skill implements Serializable {

    @Id
    private String skill;

    @ManyToMany(mappedBy = "skills")
    private Set<User> users;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
