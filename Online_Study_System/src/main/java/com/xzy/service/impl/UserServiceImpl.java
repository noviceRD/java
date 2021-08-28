package com.xzy.service.impl;

import com.xzy.mapper.UserMapper;
import com.xzy.domain.User;
import com.xzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public void save(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void removeById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateByName(String name, User user) {
        userMapper.updateUserByName(name,user);
    }

    @Override
    public void updateById(Integer id, User user) {
        userMapper.updateUserById(id,user);
    }
}
