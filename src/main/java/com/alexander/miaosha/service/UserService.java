package com.alexander.miaosha.service;

import com.alexander.miaosha.dao.UserDao;
import com.alexander.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
       User user = userDao.getById(id);
       return user;
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }

}
