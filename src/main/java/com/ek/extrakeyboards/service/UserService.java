package com.ek.extrakeyboards.service;

import com.ek.extrakeyboards.dao.UserDao;
import com.ek.extrakeyboards.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void register(User user) {
        user.setEnabled(true);
        userDao.register(user);
    }

    public User getUser(String uid) {
        return userDao.getUser(uid);
    }


}
