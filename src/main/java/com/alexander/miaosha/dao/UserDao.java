package com.alexander.miaosha.dao;

import com.alexander.miaosha.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User getById(int id);

    int addUser(User user);
}
