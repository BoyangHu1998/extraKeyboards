package com.ek.extrakeyboards.dao;

import com.ek.extrakeyboards.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public void register(User user) {
    }

    public User getUser(String uid) {
        return new User();
    }
}
